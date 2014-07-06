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
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Error creating bean with name 'springSecurityFilterChain' defined in class
 * org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
 *
 * @author luffy
 */
@Configuration
@EnableWebMvcSecurity
@Import(WebMvcSecurityConfiguration.class)
//@EnableGlobalAuthentication
//@Import({WebSecurityConfiguration.class,ObjectPostProcessorConfiguration.class})
//@EnableGlobalAuthentication
@EnableWebSecurity(debug = true)
//@Import({MyWebSecurityConfiguration.class,ObjectPostProcessorConfiguration.class,WebMvcSecurityConfiguration.class})
@EnableGlobalAuthentication
@DependsOn({"appService", "passwordEncoder"})
public class WebSecurityConfigJava extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppService appService;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private LuffyAuthenticationFilter luffyAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 下面代码可以单独抽离 
        // 配置应该是 可以支持json post 可以定义url 可以支持验证码  可以支持rememberme
//        http.csrf().disable();
//        
//        LuffyAuthenticationFilter filter = new LuffyAuthenticationFilter();        
//        filter.setAuthenticationManager(this.authenticationManager());
////        
//        filter.setUsernameParameter("username");
//        filter.setPasswordParameter("password");
//        
////        http.antMatcher("/ajaxLogin")
////                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)                
////                ;        
//        
////        http.antMatcher("/ajaxLogin").authorizeRequests();
//        
////        http.
////                formLogin()                
////                .loginProcessingUrl("/ajaxLogin")               
////                .failureHandler(new AuthenticationFailureHandler() {
////
////                    @Override
////                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
////                        response.setHeader("Content-Type", "application/json;charset=UTF-8");
////                        response.sendError(306, exception.getLocalizedMessage());
////                    }
////                })
////                .successHandler(new AuthenticationSuccessHandler() {
////
////                    @Override
////                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////                        response.setHeader("Content-Type", "application/json;charset=UTF-8");
////                    }
////                })
////                //set handler & parameters name
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/api/*")
                .authenticated()
                .antMatchers("/", "/home", "/queryUser","/ajaxLogin").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(filter, BasicAuthenticationFilter.class)                
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)                
                ;
        
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
