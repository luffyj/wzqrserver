/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.config;

import org.luffy.wzqr.wzqrserver.beans.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *  Error creating bean with name 'springSecurityFilterChain' defined in class org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
 * @author luffy
 */
@Configuration
@EnableWebMvcSecurity
@Import(WebMvcSecurityConfiguration.class)
//@EnableGlobalAuthentication
//@Import({WebSecurityConfiguration.class,ObjectPostProcessorConfiguration.class})
//@EnableGlobalAuthentication
@EnableWebSecurity(debug=true)
//@Import({MyWebSecurityConfiguration.class,ObjectPostProcessorConfiguration.class,WebMvcSecurityConfiguration.class})
@EnableGlobalAuthentication
@DependsOn({"appService","passwordEncoder"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AppService appService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
                .authorizeRequests()
                .antMatchers("/api/*")
                .authenticated()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();        
    }

    @Override    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appService).passwordEncoder(passwordEncoder);
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }
}
