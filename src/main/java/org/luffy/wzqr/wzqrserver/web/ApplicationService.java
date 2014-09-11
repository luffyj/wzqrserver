/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import freemarker.template.TemplateException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import jxl.write.WriteException;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponseWithMapdata;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.ApplicationRepository;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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

//    @PersistenceContext
//    private EntityManager entityManager;
    @RequestMapping(value = "/customMethod", method = RequestMethod.GET)
    public Page<OLog> customMethod(@Param("superid") Long superid, String type, String roleName, Date time, String loginName, Pageable pageable) {
        StringBuilder jql = new StringBuilder("select u from OLog u where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)");

        Query query = entityManagerFactory.createEntityManager().createQuery(jql.toString());
        query.setParameter("superid", superid);

        return new PageImpl(query.getResultList(), pageable, query.getMaxResults());
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private DocumentHandler documentHandler;

    //导出报表和汇总表
    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public HttpEntity<byte[]> exportSome(@RequestParam("ids") String ids) throws IOException, WriteException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        if (!(auth.getPrincipal() instanceof User)) {
            return null;
        }

        User user = (User) auth.getPrincipal();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            //获取apps
            List<Application> apps;
            if (ids.equalsIgnoreCase("all")) {

                StringBuilder jql = new StringBuilder("select u from Application u");
                handleResponseableApplicationJQL(jql);
                apps = handleResponseableApplicationQuery(entityManager.createQuery(jql.toString())).getResultList();

            } else {
                String[] ida = ids.split(",");
                apps = new ArrayList();
                for (String id : ida) {
                    if (id == null || id.length() == 0) {
                        continue;
                    }
                    Application app = this.applicationRepository.findOne(Long.parseLong(id));
                    if (app.ableReportTo(user)) {
                        apps.add(app);
                    }
                }
            }

            if (apps.isEmpty()) {
                return null;
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            this.documentHandler.exportExcel(apps, out);

            HttpHeaders header = new HttpHeaders();
            //
            header.setContentType(new MediaType("application", "vnd.ms-excel"));

            System.out.println("downloading 温州市\"580海外精英引进计划\"申报人选情况汇总表");

            header.set("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode("温州市\"580海外精英引进计划\"申报人选情况汇总表.xls", "UTF-8"));
            header.setContentLength(out.toByteArray().length);
            return new HttpEntity<>(out.toByteArray(), header);
        } finally {
            entityManager.close();
        }

    }

    @RequestMapping(value = "/report/{appid}.doc", method = RequestMethod.GET)
    public HttpEntity<byte[]> export(@PathVariable("appid") Long appid) throws IOException, TemplateException {
        //温州市“580海外精英引进计划”申报书
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        if (!(auth.getPrincipal() instanceof User)) {
            return null;
        }

        User user = (User) auth.getPrincipal();
        //这个报表必须是你的 或者是你的上级部门的
        Application app = applicationRepository.findOne(appid);
        if (app == null) {
            return null;
        }
        if (!app.ableReportTo(user)) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        if ("创业人才".equals(app.getType())) {
            this.documentHandler.export2(app, out);
        } else {
            this.documentHandler.export1(app, out);
        }

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "msword"));
        StringBuilder name = new StringBuilder("温州市“580海外精英引进计划”");
        if ("创业人才".equals(app.getType())) {
            name.append("创业类");
        } else {
            name.append("创新类");
        }
        name.append("申报书.doc");

        System.out.println("downloading " + name);

        header.set("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(name.toString(), "UTF-8"));
        header.setContentLength(out.toByteArray().length);
        return new HttpEntity<>(out.toByteArray(), header);
    }

    //counting
    /**
     * 返回的结果应该是包含所有信息的结果 { count:? status:[ { name:.. count:.. } ] }
     *
     * @return
     */
    @RequestMapping(value = "/countapplication", method = RequestMethod.GET)
    public @ResponseBody
    JsonResponse count() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        if (!(auth.getPrincipal() instanceof User)) {
            return new ErrorResponse(400, "尚未登录");
        }

        User user = (User) auth.getPrincipal();

        String[] conditions = null;
        if (null != user.getRole().getName()) {
            switch (user.getRole().getName()) {
                case Role.RolePeople:
                    conditions = new String[]{
                        "status",
                        "specialty",
                        "type",
                        "batch"
                    };
                    break;
                case Role.RoleUnit:
                    conditions = new String[]{
                        "status",
                        "specialty",
                        "type",
                        "batch"
                    };
                    break;
                case Role.RoleSubManager:
                    conditions = new String[]{
                        "status",
                        "specialty",
                        "type",
                        "batch",
                        "myorg.name"
                    };
                    break;
                case Role.RoleAdmin:
                case Role.RoleRoot:
                case Role.RoleManager:
                    conditions = new String[]{
                        "status",
                        "specialty",
                        "type",
                        "myorg.superOrg.type",//只有超管
                        "batch",
                        //            "realName",
                        //            "appOrgName",
                        "myorg.superOrg.name"//非超管的应该是myorg.name
                    };
                    break;
                default:
                    return new ErrorResponse(400, "尚未登录");
            }
        }

        HashMap result = new HashMap();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            StringBuilder jql = new StringBuilder("select count(u) from Application u");
            handleResponseableApplicationJQL(jql);
            result.put("count", handleResponseableApplicationQuery(entityManager.createQuery(jql.toString()))
                    .getSingleResult());

            for (String condition : conditions) {
                jql = new StringBuilder();
                jql.append("select count(u.")
                        .append(condition)
                        .append("),u.")
                        .append(condition)
                        .append(" from Application u");
                handleResponseableApplicationJQL(jql).append(" group by u.")
                        .append(condition);

                System.out.println(jql);

                List dataResult = handleResponseableApplicationQuery(entityManager.createQuery(jql.toString())).getResultList();
                ArrayList list = new ArrayList();
                if ("status".equals(condition)) {
                    String[] statuses = new String[]{
                        "未上报", "等待形审", "形审未过", "形审通过", "形审退回", "复审未过",
                        "复审通过", "复审退回", "评审未过", "评审通过"
                    };
                    for (String status : statuses) {
                        //寻找status
                        Object[] target = null;
                        for (Object cr : dataResult) {
                            Object[] countResult = (Object[]) cr;
                            if (status.equals(countResult[1])) {
                                target = countResult;
                                break;
                            }
                        }

                        if (target != null) {
                            HashMap toJson = new HashMap();
                            toJson.put("name", target[1]);
                            toJson.put("count", target[0]);
                            list.add(toJson);
                            continue;
                        }

                        if (status.equals("未上报")) {
                            continue;
                        }

                        HashMap toJson = new HashMap();
                        toJson.put("name", status);
                        toJson.put("count", 0);
                        list.add(toJson);
                    }
                } else {
                    for (Object cr : dataResult) {
                        Object[] countResult = (Object[]) cr;
                        HashMap toJson = new HashMap();
                        toJson.put("name", countResult[1]);
                        toJson.put("count", countResult[0]);
                        list.add(toJson);
                    }
                }
                result.put(condition, list);
            }
            return new JsonResponseWithMapdata(result);
        } finally {
            entityManager.close();
        }
    }
    
    interface WorkingData{
        void setValues(Application app,MultipartFile file) throws IOException;

        public byte[] getValue(Application app);

        public MediaType getMediaType();

        public String getFileName(Application app);
    }
    
    private WorkingData attachment = new WorkingData(){

        @Override
        public void setValues(Application app, MultipartFile file) throws IOException {
            app.setAttachment(file.getBytes());
        }

        @Override
        public byte[] getValue(Application app) {
            return app.getAttachment();
        }

        @Override
        public MediaType getMediaType() {
            return new MediaType("application", "pdf");
        }

        @Override
        public String getFileName(Application app) {
            return new StringBuilder().append(app.getBatch())
                .append(app.getRealName())
                .append("的申请附件.pdf").toString();
        }
    };
    
    private WorkingData picture = new WorkingData(){

        @Override
        public void setValues(Application app, MultipartFile file) throws IOException {
            BufferedImage image = ImageIO.read(file.getInputStream());
            ByteArrayOutputStream bys = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", bys);
            app.setPicture(bys.toByteArray());
        }

        @Override
        public byte[] getValue(Application app) {
            return app.getPicture();
        }

        @Override
        public MediaType getMediaType() {
            return MediaType.IMAGE_PNG;
        }

        @Override
        public String getFileName(Application app) {
            return new StringBuilder().append(app.getBatch())
                .append(app.getRealName())
                .append(".png").toString();
        }
        
    };
    
    private HttpEntity<byte[]> downloadData(Long appid,WorkingData data) throws UnsupportedEncodingException {
        if (appid == null) {
            return null;
        }
        Application app = applicationRepository.findOne(appid);
        if (app == null) {
            return null;
        }
        byte[] documentBody = data.getValue(app);
        
        if (documentBody == null) {
            return null;
        }

        HttpHeaders header = new HttpHeaders();
        header.setContentType(data.getMediaType());
        String name = data.getFileName(app);

        System.out.println("downloading " + name);

        header.set("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(name, "UTF-8"));
        header.setContentLength(documentBody.length);
        return new HttpEntity<>(documentBody, header);
    }
    
    private HttpEntity uploadData(MultipartFile pdf, Long appid,WorkingData data) throws IOException {
        //{"code":200,"originalMessage":"上传成功！"}
        System.out.println(pdf.getOriginalFilename() + " uploaded!");
        int errorBase = 590;

        if (appid == null) {
            return new ErrorResponse(errorBase + 0, "上传附件必须指定申报信息").toHttpEntity();
        }
        Application app = applicationRepository.findOne(appid);
        if (app == null) {
            return new ErrorResponse(errorBase + 1, "找不到指定的申报信息").toHttpEntity();
        }
        
        data.setValues(app, pdf);

        this.applicationRepository.save(app);

        return new JsonResponse(200,"上传成功！").toHttpEntity();
    }

    // 附件上传和下载
    // @ModelAttribute( "uploadForm" ) FileUploadForm uploadForm
    @RequestMapping(value = "/uploadattachment", method = RequestMethod.POST)
    public HttpEntity upload(@RequestParam("file") MultipartFile pdf, @RequestParam("id") Long appid) throws IOException {
        return this.uploadData(pdf, appid, attachment);
    }

    @RequestMapping(value = "/attachment/{appid}.pdf", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadPDF(@PathVariable("appid") Long appid) throws UnsupportedEncodingException {
        return this.downloadData(appid, attachment);
    }
    
    @RequestMapping(value = "/uploadpicture", method = RequestMethod.POST)
    public HttpEntity uploadPicture(@RequestParam("file") MultipartFile pdf, @RequestParam("id") Long appid) throws IOException {
        return this.uploadData(pdf, appid, picture);
    }

    @RequestMapping(value = "/picture/{appid}.png", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadPicture(@PathVariable("appid") Long appid) throws UnsupportedEncodingException {
        return this.downloadData(appid, picture);
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
            return new ErrorResponse(errorBase + 0, "更改所有者必须使用更多的参数");
        }

        if (auth.getPrincipal() instanceof User) {
            User cuser = (User) auth.getPrincipal();
            if (!Role.RoleUnit.equals(cuser.getRole().getName())) {
                return new ErrorResponse(errorBase + 2, "你必须是一个申报单位");
            }
            Application app = this.applicationRepository.findOne(appid);
            if (app == null) {
                return new ErrorResponse(errorBase + 1, "找不到指定的申报信息");
            }

            if (!app.getMyorg().getId().equals(cuser.getOrg().getId())) {
                return new ErrorResponse(errorBase + 2, "不在你的管辖范围");
            }

            User user = this.userRepository.findByLoginName(username);

            if (user == null) {
                if (password.length() == 0) {
                    return new ErrorResponse(errorBase + 3, "新增用户需要提供密码");
                }
                user = new User();
                user.setLoginName(username);
                user.setOrg(cuser.getOrg());
                user = this.userRepository.save(user);
                if (userService.initUserPassword(user.getId(), password, true).getCode() != 200) {
                    return new ErrorResponse(errorBase + 4, "初始化用户失败");
                }
            } else {
                if (!user.getOrg().getId().equals(cuser.getOrg().getId())) {
                    return new ErrorResponse(errorBase + 5, "用户已存在，且非" + cuser.getOrg().getName() + "申报人");
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
                return new ErrorResponse(errorBase + 1, "找不到指定的申报信息");
            }
            User cuser = (User) auth.getPrincipal();

            //只有单位才可以上报
            if (!Role.RoleUnit.equals(cuser.getRole().getName())) {
                return new ErrorResponse(errorBase + 2, "你必须是一个申报单位");
            }

            if (!app.getMyorg().getId().equals(cuser.getOrg().getId())) {
                return new ErrorResponse(errorBase + 3, "你无法越权上报");
            }

            if (!app.getStatus().equals("未上报")
                    && !app.getStatus().contains("退回")) {
                return new ErrorResponse(errorBase + 4, "错误的状态");
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
            app.setSubmitDate(new Date());

//            app.setReturnOrg(null);
//            app.setReturnReason(null);
//            app.setUnitApproveReason(null);
//            app.setUnitApproveSupport(null);
//            app.setOrgApproveReason(null);
//            app.setOrgApproveSupport(null);
//            app.setManagerReason(null);
//            app.setPogeReason(null);
//            app.setPoge(false);
            this.applicationRepository.save(app);

            return new JsonResponse(200, "成功");
        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    public int checkResult(int input) {
        return input << 24 >> 24;
    }

    public int checkType(int input) {
        return input >> 8;
    }

    public int computusResult(int type, int result) {
        return (type << 8) | result;
    }

    // 形审 复审 评审
    // 评审没有退回
    /**
     * 评审没有退回
     *
     * 逻辑修正 重审就是重审 要在参数内提供明确的参数
     *
     * 形审通过 可以重审 为形审未过 形审退回
     *
     * 复审通过 可以重审 为复审未过 复审退回 评审通过 评审退回
     *
     * 评审通过 可以重审 评审通过 评审未过 复审通过 评审未过 可以重审 评审通过 评审未过 复审通过
     *
     * 鉴于现在的表现形式 应该通过增加result类型的方式达成目的
     *
     * --------|--------|--表示是否操作上级--|---操作数--
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

        int type = this.checkType(result);
        result = this.checkResult(result);

        if (result != 0 && result != 1 && result != 2) {
            return new ErrorResponse(errorBase + 1, "错误的参数");
        }

        //照片？？
        //状态也是动作！
        if (auth.getPrincipal() instanceof User) {
            Application app = this.applicationRepository.findOne(appid);
            if (app == null) {
                return new ErrorResponse(errorBase + 2, "找不到指定的申报信息");
            }

            User cuser = (User) auth.getPrincipal();

            switch (app.getStatus()) {
//                case "未上报":
//                    return new ErrorResponse(572, "该申报信息尚未上报");
                case "等待形审":
                case "形审未过":
                    //形审通过
                    //执行的人为 次级机构管理员
                    return doXingshen(errorBase, cuser, app, result, reason, request);
                //部门管理员可以退回
                case "形审通过":
                    if (type == 1) {
                        return doXingshen(errorBase, cuser, app, result, reason, request);
                    }
//                case "复审通过":
                case "复审未过":
//                case "复审退回":
                    //通过 和退回 的无法重审
                    //复审 或者重审
                    return doFushen(errorBase, cuser, app, result, reason, request);
                case "复审通过":
                    if (type == 1) {
                        return doFushen(errorBase, cuser, app, result, reason, request);
                    }
                case "评审通过":
                case "评审未过":
                    if (type == 1) {
                        return doFushen(errorBase, cuser, app, result, reason, request);
                    }
                    if (!Role.RoleRoot.equals(cuser.getRole().getName())) {
                        return new ErrorResponse(errorBase + 3, "你必须是一个市委组织部管理员");
                    }
                    switch (result) {
                        case 2:
                            return returnApp(app, cuser, reason, "评审", "", request);
                        case 1:
                            return yesApp(app, cuser, reason, "评审", "", request);
                        default:
                            return noApp(app, cuser, reason, "评审", "", request);
                    }
                default:
                    return new ErrorResponse(errorBase + 5, "当前状态无法操作");
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
        if (oper.getOrg() != null) {
            app.setReturnOrg(oper.getOrg().getName());
        } else {
            app.setReturnOrg("未知");
        }
        String newStatus = type + "退回";
        switch (type) {
            case "形审":
                app.setUnitApproveReason(reason);
                break;
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        if (app.getStatus().contains("评审") && type.equals("复审")) {
            CS = true;
        }
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
                break;
            case "评审":
                app.setManagerReason(reason);
                break;
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        if (app.getStatus().contains("评审") && type.equals("复审")) {
            CS = true;
        }
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
                break;
            case "评审":
                app.setManagerReason(reason);
                break;
            default:
                app.setOrgApproveReason(reason);
        }
        boolean CS = app.getStatus().contains(type);
        if (app.getStatus().contains("评审") && type.equals("复审")) {
            CS = true;
        }
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

    private StringBuilder handleResponseableApplicationJQL(StringBuilder jql) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        jql.append(" where u.status <> '已删除'");

        if (null != user.getRole().getName()) {
            switch (user.getRole().getName()) {
                case Role.RolePeople:
                    jql.append("  and ( u.owner.id = :userid )");
                    break;
                case Role.RoleUnit:
                    jql.append(" and ( u.myorg.id = :superid)");
                    break;
                case Role.RoleSubManager:
                    jql.append(" and u.status <> '未上报' and (u.myorg.superOrg.id = :superid)");
                    break;
                case Role.RoleAdmin:
                case Role.RoleRoot:
                case Role.RoleManager:
                    jql.append(" and u.status <> '未上报'");
                    break;
            }
        }
        return jql;
    }

    private Query handleResponseableApplicationQuery(Query query) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        if (null != user.getRole().getName()) {
            switch (user.getRole().getName()) {
                case Role.RolePeople:
                    query.setParameter("userid", user.getId());
                    break;
                case Role.RoleUnit:
                case Role.RoleSubManager:
                    query.setParameter("superid", user.getOrg().getId());
                    break;
                case Role.RoleAdmin:
                case Role.RoleRoot:
                case Role.RoleManager:
                    break;
            }
        }
        return query;
    }

    private JsonResponse doXingshen(int errorBase, User cuser, Application app, int result, String reason, HttpServletRequest request) {
        if (!Role.RoleSubManager.equals(cuser.getRole().getName())) {
            return new ErrorResponse(errorBase + 3, "你必须是一个次级机构管理员");
        }
        if (!app.getMyorg().getSuperOrg().getId().equals(cuser.getOrg().getId())) {
            return new ErrorResponse(errorBase + 4, "你无权进行这个操作");
        }
        switch (result) {
            case 2:
                return returnApp(app, cuser, reason, "形审", "", request);
            case 1:
                return yesApp(app, cuser, reason, "形审", "", request);
            default:
                return noApp(app, cuser, reason, "形审", "", request);
        }
    }

    private JsonResponse doFushen(int errorBase, User cuser, Application app, int result, String reason, HttpServletRequest request) {
        switch (result) {
            case 2:
                if (!Role.RoleManager.equals(cuser.getRole().getName())
                        && !Role.RoleRoot.equals(cuser.getRole().getName())
                        && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                    return new ErrorResponse(errorBase + 3, "你必须是一个部门管理员或者市委组织部管理员");
                }
                return returnApp(app, cuser, reason, "复审", "", request);
            case 1:
                if (!Role.RoleRoot.equals(cuser.getRole().getName())
                        && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                    return new ErrorResponse(errorBase + 3, "你必须是一个市委组织部管理员");
                }
                return yesApp(app, cuser, reason, "复审", "", request);
            default:
                if (!Role.RoleRoot.equals(cuser.getRole().getName())
                        && !Role.RoleAdmin.equals(cuser.getRole().getName())) {
                    return new ErrorResponse(errorBase + 3, "你必须是一个市委组织部管理员");
                }
                return noApp(app, cuser, reason, "复审", "", request);
        }
    }
}
