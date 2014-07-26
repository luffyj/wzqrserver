/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.ApplicationRepository;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 包含业务
 *
 * @author luffy
 */
@Controller
public class ApplicationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LogRepository logRepository;

    // 附件上传和下载
    // @ModelAttribute( "uploadForm" ) FileUploadForm uploadForm
    @RequestMapping(value = "/uploadattachment", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse upload(@RequestParam("file") MultipartFile pdf, @RequestParam("appid") Long appid) throws IOException {
        System.out.println(pdf.getOriginalFilename() + " uploaded!");
        int errorBase = 590;

        if (appid == null) {
            return new ErrorResponse(errorBase+0, "上传附件必须指定申报信息");
        }
        Application app = applicationRepository.findOne(appid);
        if (app == null) {
            return new ErrorResponse(errorBase+1, "找不到指定的申报信息");
        }

        app.setAttachment(pdf.getBytes());

        this.applicationRepository.save(app);

        return new JsonResponse(200);
    }

    @RequestMapping(value = "/attachment/{appid}.pdf", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadPDF(@PathVariable("appid") Long appid) {
        if (appid == null) {
            return null;
        }
        Application app = applicationRepository.findOne(appid);
        if (app == null) {
            return null;
        }
        byte[] documentBody = app.getAttachment();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        StringBuilder name = new StringBuilder();
        name.append(app.getBatch())
                .append(app.getRealName())
                .append("的申请附件.pdf");

        header.set("Content-Disposition",
                "attachment; filename=" + name);
        header.setContentLength(documentBody.length);
        return new HttpEntity<>(documentBody, header);
    }

    //更换用户
    //只有申报单位 才可以做这个事
    @RequestMapping(value = "/cappowner", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse changeOwner(
            @RequestParam("appid") Long appid,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        
        int errorBase = 580;

        if (appid == null || username == null) {
            return new ErrorResponse(errorBase+0, "更改所有者必须使用更多的参数");
        }

        if (auth.getPrincipal() instanceof User) {
            User cuser = (User) auth.getPrincipal();
            if (!Role.RoleUnit.equals(cuser.getRole().getName())) {
                return new ErrorResponse(errorBase+2, "你必须是一个申报单位");
            }
            Application app = this.applicationRepository.findOne(appid);
            if (app == null) {
                return new ErrorResponse(errorBase+1, "找不到指定的申报信息");
            }

            if (!app.getMyorg().getId().equals(cuser.getOrg().getId())) {
                return new ErrorResponse(errorBase+2, "不在你的管辖范围");
            }

            User user = this.userRepository.findByLoginName(username);

            if (user == null) {
                if (password.length() == 0) {
                    return new ErrorResponse(errorBase+3, "新增用户需要提供密码");
                }
                user = new User();
                user.setLoginName(username);
                user.setOrg(cuser.getOrg());
                user = this.userRepository.save(user);
                if (userService.initUserPassword(user.getId(), password, true).getCode() != 200) {
                    return new ErrorResponse(errorBase+4, "初始化用户失败");
                }
            } else {
                if (!user.getOrg().getId().equals(cuser.getOrg().getId())) {
                    return new ErrorResponse(errorBase+5, "用户已存在，且非" + cuser.getOrg().getName() + "申报人");
                }
            }

            app.setOwner(user);
            this.applicationRepository.save(app);

            JsonResponse jr = new JsonResponse();
            jr.setCode(200);
            jr.setOriginalMessage("成功");
            return jr;

        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    //核心业务逻辑了
    //上报
    @RequestMapping(value = "/submitapp", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse submit(
            @RequestParam("appid") Long appid, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        
        int errorBase = 560;

        if (appid == null) {
            return new ErrorResponse(errorBase, "上报必须输入足够的参数");
        }

        if (auth.getPrincipal() instanceof User) {
            Application app = this.applicationRepository.findOne(appid);
            if (app == null) {
                return new ErrorResponse(errorBase+1, "找不到指定的申报信息");
            }
            User cuser = (User) auth.getPrincipal();

            //只有单位才可以上报
            if (!Role.RoleUnit.equals(cuser.getRole().getName())) {
                return new ErrorResponse(errorBase+2, "你必须是一个申报单位");
            }
            
            if(!app.getMyorg().getId().equals(cuser.getOrg().getId())){
                return new ErrorResponse(errorBase+3, "你无法越权上报");
            }

            if (!app.getStatus().equals("未上报")
                    && !app.getStatus().contains("退回")) {
                return new ErrorResponse(errorBase+4, "错误的状态");
            }

            OLog ol = new OLog(cuser, request, "上报");
            ol.opApplication(app);
            StringBuilder sb = new StringBuilder();
            if (!app.getStatus().equals("未上报")) {
                sb.append("重新");
            }
            sb.append("上报了")
                    .append(app.getRealName())
                    .append("的审核信息");
            ol.setMessage(sb.toString());
            this.logRepository.save(ol);

            app.setStatus("等待形审");

            app.setReturnOrg(null);
            app.setReturnReason(null);
            app.setUnitApproveReason(null);
            app.setUnitApproveSupport(null);
            app.setOrgApproveReason(null);
            app.setOrgApproveSupport(null);
            app.setManagerReason(null);
            this.applicationRepository.save(app);

            return new JsonResponse(200, "成功");
        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    // 形审 复审 评审
    // 评审没有退回
    /**
     * 评审没有退回
     *
     * @param result -1 退回 0 未过 1 通过
     * @param reason 审核原因
     * @param appid 被审核的app
     */
    @RequestMapping(value = "/approvalapp", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse approval(
            @RequestParam("appid") Long appid,
            @RequestParam("reason") String reason,
            @RequestParam("result") int result, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        
        int errorBase = 570;

        if (appid == null || reason == null) {
            return new ErrorResponse(errorBase, "审核必须输入足够的参数");
        }

        if (result != 0 && result != 1 && result != -1) {
            return new ErrorResponse(errorBase+1, "错误的参数");
        }

        //照片？？
        //状态也是动作！
        if (auth.getPrincipal() instanceof User) {
            Application app = this.applicationRepository.findOne(appid);
            if (app == null) {
                return new ErrorResponse(errorBase+2, "找不到指定的申报信息");
            }

            User cuser = (User) auth.getPrincipal();

            switch (app.getStatus()) {
//                case "未上报":
//                    return new ErrorResponse(572, "该申报信息尚未上报");
                case "等待形审":
                case "形审未过":
                    //形审通过
                    //执行的人为 次级机构管理员                    
                    if (!Role.RoleSubManager.equals(cuser.getRole().getName())) {
                        return new ErrorResponse(errorBase+3, "你必须是一个次级机构管理员");
                    }
                    if (!app.getMyorg().getSuperOrg().getId().equals(cuser.getOrg().getId())) {
                        return new ErrorResponse(errorBase+4, "你无权进行这个操作");
                    }
                    switch (result) {
                        case -1:
                            return returnApp(app, cuser, reason, "形审", "", request);
                        case 1:
                            return yesApp(app, cuser, reason, "形审", "", request);
                        default:
                            return noApp(app, cuser, reason, "形审", "", request);
                    }
                //部门管理员可以退回
                case "形审通过":
//                case "复审通过":
                case "复审未过":
//                case "复审退回":
                    //通过 和退回 的无法重审
                    //复审 或者重审
                    switch (result) {
                        case -1:
                            if (!Role.RoleManager.equals(cuser.getRole().getName())
                                    && !Role.RoleRoot.equals(cuser.getRole().getName())
                                    && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                                return new ErrorResponse(errorBase+3, "你必须是一个部门管理员或者市委组织部管理员");
                            }
                            return returnApp(app, cuser, reason, "复审", "", request);
                        case 1:
                            if (!Role.RoleRoot.equals(cuser.getRole().getName())
                                    && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                                return new ErrorResponse(errorBase+3, "你必须是一个市委组织部管理员");
                            }
                            return yesApp(app, cuser, reason, "复审", "", request);
                        default:
                            if (!Role.RoleRoot.equals(cuser.getRole().getName())
                                    && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                                return new ErrorResponse(errorBase+3, "你必须是一个市委组织部管理员");
                            }
                            return noApp(app, cuser, reason, "复审", "", request);
                    }
                case "复审通过":
                case "评审通过":
                case "评审未过":
                    if (!Role.RoleRoot.equals(cuser.getRole().getName())) {
                        return new ErrorResponse(errorBase+3, "你必须是一个市委组织部管理员");
                    }
                    switch (result) {
//                        case -1:
//                            return returnApp(app, cuser, reason, "形审", "");
                        case 1:
                            return yesApp(app, cuser, reason, "评审", "", request);
                        default:
                            return noApp(app, cuser, reason, "评审", "", request);
                    }
                default:
                    return new ErrorResponse(errorBase+5, "当前状态无法操作");
            }
        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    /**
     * 退回
     */
    private JsonResponse returnApp(Application app, User oper, String reason, String type, String other, HttpServletRequest request) {
        app.setReturnReason(reason);
        if(oper.getOrg()!=null)
            app.setReturnOrg(oper.getOrg().getName());
        else
            app.setReturnOrg("未知");;
        String newStatus = type + "退回";
        switch (type) {
            case "形审":
                app.setUnitApproveReason(reason);
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        app.setStatus(newStatus);
        this.applicationRepository.save(app);

        OLog ol = new OLog(oper, request, "审核");
        ol.opApplication(app);

        StringBuilder sb = new StringBuilder();
        if (CS) {
            sb.append("重审");
        }
        sb.append(type)
                .append("退回了")
                .append(app.getRealName())
                .append("的审核信息");

        ol.setMessage(sb.toString());
        this.logRepository.save(ol);
        return new JsonResponse(200, "成功");
    }

    private JsonResponse noApp(Application app, User oper, String reason, String type, String other, HttpServletRequest request) {
        String newStatus = type + "未过";
        switch (type) {
            case "形审":
                app.setUnitApproveReason(reason);
            case "评审":
                app.setManagerReason(reason);
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        app.setStatus(newStatus);
        this.applicationRepository.save(app);

        OLog ol = new OLog(oper, request, "审核");
        ol.opApplication(app);

        StringBuilder sb = new StringBuilder();
        if (CS) {
            sb.append("重审");
        }
        sb.append(type)
                .append("未过了")
                .append(app.getRealName())
                .append("的审核信息");

        ol.setMessage(sb.toString());
        this.logRepository.save(ol);
        return new JsonResponse(200, "成功");
    }

    private JsonResponse yesApp(Application app, User oper, String reason, String type, String other, HttpServletRequest request) {
        String newStatus = type + "通过";
        switch (type) {
            case "形审":
                app.setUnitApproveReason(reason);
            case "评审":
                app.setManagerReason(reason);
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        app.setStatus(newStatus);
        this.applicationRepository.save(app);

        OLog ol = new OLog(oper, request, "审核");
        ol.opApplication(app);

        StringBuilder sb = new StringBuilder();
        if (CS) {
            sb.append("重审");
        }
        sb.append(type)
                .append("通过了")
                .append(app.getRealName())
                .append("的审核信息");

        ol.setMessage(sb.toString());
        this.logRepository.save(ol);
        return new JsonResponse(200, "成功");
    }
}
