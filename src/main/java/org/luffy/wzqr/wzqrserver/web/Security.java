/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.LoginRequest;
import org.luffy.wzqr.wzqrserver.beans.bean.LoginResponse;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author luffy
 */
@Controller
@Order(90)
//@DependsOn("authenticationManager")
public class Security {
    
    @Autowired
    private UserRepository userRepository;

    public Security() {
        super();
    }

    @RequestMapping("/queryUser")
    @ResponseBody
    public Object queryUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }

        if (auth.getPrincipal() instanceof User) {
            User user = (User) auth.getPrincipal();
            user.setLastLogin(new Date());
            userRepository.save(user);
            if(user.getOrg()!=null){
                user.getOrg().setManager(null);
                user.getOrg().setSuperOrg(null);
            }
            return user;
        }

        return auth.getPrincipal();
    }

//    @Autowired
    AuthenticationManager authenticationManager;

    @ResponseBody
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    public JsonResponse mosLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        JsonResponse response = null;

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            token.setDetails(new WebAuthenticationDetails(request));

            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            if (auth.isAuthenticated()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setCode(JsonResponse.SUCCESS);
                response = loginResponse;
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setCode(JsonResponse.FAILED);
                response = errorResponse;
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(JsonResponse.ERROR);
            response = errorResponse;
        }
        return response;
    }

}
