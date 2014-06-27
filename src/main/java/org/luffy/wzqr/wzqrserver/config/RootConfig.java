/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.config;

import org.luffy.lib.libspring.config.JpaConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 这个配置将是所有spring 公用的配置
 * 
 * @author luffy
 */
@Configuration
@EnableWebMvcSecurity
@EnableJpaRepositories("org.luffy.wzqr.wzqrserver.repositories")
@Import({JpaConfig.class,WebSecurityConfig.class})
@ComponentScan("org.luffy.wzqr.wzqrserver.beans")
public class RootConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    public ViewResolver viewResolver(){
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//        bean.setPrefix("/WEB-INF/classes/templates/");
//        bean.setSuffix(".html");
//        return bean;
//    }
    
}
