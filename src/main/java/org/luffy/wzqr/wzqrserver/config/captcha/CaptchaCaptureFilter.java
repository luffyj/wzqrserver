/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.config.captcha;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author luffy
 */
public class CaptchaCaptureFilter extends OncePerRequestFilter {

    @Autowired
    private Environment env;

    private RequestMatcher captchaAbleMatcher = new RequestMatcher() {

        @Override
        public boolean matches(HttpServletRequest request) {
            return request.getMethod().equalsIgnoreCase("POST") && request.getPathInfo().equals("/login");
        }
    };

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        // 添加需要保护的urls
        if (captchaAbleMatcher.matches(req) && (!env.acceptsProfiles("test") || env.acceptsProfiles("testcaptcha"))) {
            if (!SimpleImageCaptchaServlet.validateResponse(req, req.getParameter("jcaptcha"))) {
                res.sendError(410, "验证码错误");
//                throw new ServletException("验证码错误");
                return;
            }
        }

        // Proceed with the remaining filters
        chain.doFilter(req, res);
    }
}
