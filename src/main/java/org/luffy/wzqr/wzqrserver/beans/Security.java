/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.luffy.wzqr.wzqrserver.beans.bean.ErrorResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.beans.bean.LoginRequest;
import org.luffy.wzqr.wzqrserver.beans.bean.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
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
                loginResponse.setResponseCode(JsonResponse.SUCCESS);
                response = loginResponse;
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setResponseCode(JsonResponse.FAILED);
                response = errorResponse;
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setResponseCode(JsonResponse.ERROR);
            response = errorResponse;
        }
        return response;
    }

}
