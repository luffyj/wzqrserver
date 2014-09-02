/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import java.util.Objects;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author luffy
 */
@Controller
public class UserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/setPassword")
    @ResponseBody
    public JsonResponse setPassword(
            @RequestParam("user") String user,
            @RequestParam("password") String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        if (user == null || user.length() < 0) {
            return new ErrorResponse(510, "请输入用户名");
        }
        if (password == null || password.length() < 0) {
            return new ErrorResponse(510, "请输入密码");
        }
        if (auth.getPrincipal() instanceof User) {
            User ap = (User) auth.getPrincipal();
            User target = userRepository.findByLoginName(user);

            //必须是ap管理target
            if (Objects.equals(target.getOrg().getSuperOrg().getId(), ap.getOrg().getId())) {
                target.setPassword(passwordEncoder.encode(password));
                userRepository.save(target);
                return new JsonResponse(200 ,"修改成功！");
            } else {
                return new ErrorResponse(400, "尚未登录");
            }
        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    @RequestMapping("/cpassword")
    @ResponseBody
    public JsonResponse changePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("password") String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return new ErrorResponse(400, "尚未登录");
        }
        if (auth.getPrincipal() instanceof User) {
            User ap = (User) auth.getPrincipal();
            User user = userRepository.findOne(ap.getId());

            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                return new ErrorResponse(540, "密码不正确");
            }

            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return new JsonResponse(200);
        } else {
            return new ErrorResponse(400, "尚未登录");
        }
    }

    @RequestMapping(value = "/initUserPassword", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse initUserPassword(
            @RequestParam("userid") Long userid,
            @RequestParam("password") String password,
            @RequestParam("people") boolean people) {

        //TODO 权限检查
        User user = userRepository.findOne(userid);

        Organization org = user.getOrg();
        if (org == null) {
            ErrorResponse er = new ErrorResponse();
            er.setCode(502);
            er.setOriginalMessage("部门尚未设置");
            return er;
        }

        //根据部门类型获取角色
        if (org.getType() == null) {
            user.setRole(roleRepository.findByName(Role.RoleRoot));
        } else if ("部门".equals(org.getType())) {
            user.setRole(roleRepository.findByName(Role.RoleManager));
        } else if ("申报单位".equals(org.getType())) {
            if (people) {
                user.setRole(roleRepository.findByName(Role.RolePeople));
            } else {
                user.setRole(roleRepository.findByName(Role.RoleUnit));
            }
            //'县市区', '高校', '科研院所', '国有企业'
        } else if ("县市区".equals(org.getType())
                || "高校".equals(org.getType())
                || "科研院所".equals(org.getType())
                || "国有企业".equals(org.getType())) {
            user.setRole(roleRepository.findByName(Role.RoleSubManager));
        } else {
            user.setRole(roleRepository.findByName(Role.RolePeople));
        }

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        JsonResponse jr = new JsonResponse();
        jr.setCode(200);
        jr.setOriginalMessage("成功");
        return jr;
    }

}
