/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * @author luffy
 */
@Configuration
@EnableGlobalAuthentication
@ComponentScan("org.luffy.wzqr.wzqrserver.web")
@EnableWebMvc
@EnableSpringDataWebSupport
public class MVCConfig extends  WebMvcConfigurationSupport{
    
//    @Inject
//    private ServletContextTemplateResolver templateResolver;
    
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver bean= new ThymeleafViewResolver();
        bean.setTemplateEngine(this.templateEngine());
        bean.setOrder(1);
        return bean;
    }
    
//    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setTemplateResolver(this.templateResolver());
        return bean;
    }
    
//    @Bean
    public ServletContextTemplateResolver templateResolver(){
        ServletContextTemplateResolver bean = new ServletContextTemplateResolver();
        bean.setPrefix("/WEB-INF/templates/");
        bean.setSuffix(".html");
//        bean.setTemplateMode("HTML5");
        return bean;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {        
        configurer.enable();
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/loginPage").setViewName("login");
    }

    
}
