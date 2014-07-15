/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @AuthenticationPrincipal @CurrentUser @author luffy
 */
@Controller
public class GreetingController {
    
    public GreetingController() {
        super();
    }
    
    @RequestMapping("/")
    public void home(HttpServletResponse reponse, HttpServletRequest request) throws IOException, ServletException {
        if(request.getRequestURI().endsWith("/")){
            request.getRequestDispatcher("/index.html").forward(request, reponse);
        }else{
            reponse.sendRedirect(request.getRequestURI()+"/");
        }        
    }
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
}
