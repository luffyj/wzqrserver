/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.RegisterRequest;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.repositories.RegisterRequestRepository;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.luffy.wzqr.wzqrserver.web.utils.RegisterBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author luffy
 */
@Controller
public class RegisterService {

    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private RegisterRequestRepository registerRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("subNames")
    public List<Organization> subNames() {
        return this.orgRepository.findSubOrg(null).getContent();
    }

    @ModelAttribute("bean")
    public RegisterBean bean() {
        return new RegisterBean();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String openPage() {
        return "register";
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse doRegister(RegisterBean bean, HttpServletRequest request) {
        Organization org = orgRepository.findByName(bean.getName());
        if (org != null) {
            return new JsonResponse(404, "申报单位已经存在，无法再申请注册。");
        }

        User user = userRepository.findByLoginName(bean.getUsername());

        if (user != null) {
            return new JsonResponse(405, "用户名已存在，请使用其他用户名申请。");
        }

        RegisterRequest rr = new RegisterRequest();
        bean.setSuperOrg(this.orgRepository.findOne(bean.getSuperPk()));
        rr.setBean(bean);        
        rr.setCreateDate(new Date());
        rr.setStatus("申请");

        rr = registerRequestRepository.save(rr);

        OLog lg = new OLog(null, request, "申请注册");
        lg.setTargetpk(rr.getId());
        lg.setMessage("申请注册" + bean.getName());
        this.logRepository.save(lg);

        return new JsonResponse(200, "已将注册信息提交到管理员进行审核，审核结果会以短信或者邮件的形式通知您，请耐心等候！");
    }

    @RequestMapping(value = "/resultregister", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse doResult(@RequestParam("target") Long target, @RequestParam("ok") boolean ok, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        if (!(auth.getPrincipal() instanceof User)) {
            return null;
        }

        User user = (User) auth.getPrincipal();

        if (!user.getRole().getName().equals(Role.RoleRoot)
                && !user.getRole().getName().equals(Role.RoleAdmin)) {
            return new ErrorResponse(901, "只有管理员可以做这个事。");
        }

        RegisterRequest rr = this.registerRequestRepository.findOne(target);

        if (rr == null) {
            return new ErrorResponse(902, "找不到这个申请");
        }

        if (!ok) {
            rr.setStatus("拒绝");
            rr.setChangeDate(new Date());
            registerRequestRepository.save(rr);

            OLog lg = new OLog(user, request, "处理注册");
            lg.setTargetpk(target);
            lg.setMessage("拒绝了" + rr.getBean().getName() + "的注册申请");
            this.logRepository.save(lg);
            return new ErrorResponse(200, "完成");
        } else {

            Organization neworg = new Organization();
            neworg.setContact(rr.getBean().getContact());
            neworg.setDescription(rr.getBean().getDescription());
            neworg.setName(rr.getBean().getName());
            neworg.setType("申报单位");
            neworg.setSuperOrg(rr.getBean().getSuperOrg());
            neworg = this.orgRepository.save(neworg);

            User newuser = new User();
            newuser.setContact(rr.getBean().getContact());
            newuser.setLoginName(rr.getBean().getUsername());
            newuser.setOrg(neworg);
            newuser.setPosition(rr.getBean().getContactJob());
            newuser.setRealName(rr.getBean().getContact().getPeople());
            newuser.setRole(roleRepository.findByName(Role.RoleUnit));
            newuser.setPassword(passwordEncoder.encode(rr.getBean().getPassword()));
            newuser = this.userRepository.save(newuser);

            neworg.setManager(newuser);
            this.orgRepository.save(neworg);

            rr.setStatus("允许");
            rr.setChangeDate(new Date());
            registerRequestRepository.save(rr);

            OLog lg = new OLog(user, request, "处理注册");
            lg.setTargetpk(target);
            lg.setMessage("同意了" + rr.getBean().getName() + "的注册申请");
            this.logRepository.save(lg);

            return new ErrorResponse(200, "完成");
        }
    }

}
