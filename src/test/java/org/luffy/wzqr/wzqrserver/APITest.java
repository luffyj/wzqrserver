/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver;

import hello.IRuntimeConfig;
import hello.WebTest;
import java.security.Principal;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author luffy
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {RestDataConfig.class,RootConfig.class,IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class APITest extends WebTest{
    
    public APITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
//    @Before
//    public void setUp() {
//    }
    
//    @InjectMocks
    @Inject
    private UserRepository userRepository;
    @Inject
    private PasswordEncoder passwordEncoder;
    private final String tempUsername = "oonoway";
    
    
    
    @Before
    public void ss1(){
        User user = new User();
        user.setLoginName(tempUsername);
        user.setPassword(passwordEncoder.encode("1"));
        userRepository.save(user);
//        when(userRepository.findByLoginName(same("mocku"))).thenReturn(null);
        
        
        
    }
    
    
    @After
    public void tearDown() {
        userRepository.delete(userRepository.findByLoginName(tempUsername));
    }

     @Test
     public void security() throws Exception {
         WebSecurityConfiguration a;
          assertTrue(true);
         MockHttpSession session = (MockHttpSession) this.mockMvc.perform(get("/api/user"))
                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("http://localhost/login"))
                 .andReturn().getRequest().getSession();
//         Redirected URL = http://localhost/login
         
         //bad password
         MockHttpServletRequest request = this.mockMvc.perform(post("/login").session(session)
                 .param("username", tempUsername).param("password", "222"))
                 .andDo(print())
                 .andExpect(status().isForbidden())
                 .andReturn().getRequest();
//                 .andExpect(redirectedUrl("/login?error"));
         
         CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
         
         Principal pl= this.mockMvc.perform(post("/login").session(session)
                 .param("username", tempUsername).param("password", "1")
                 .param(token.getParameterName(), token.getToken())
                    )
                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("http://localhost/api/user"))
                 .andReturn().getRequest().getUserPrincipal();
//                 .andReturn().getRequest().getSession();
         
         
         this.mockMvc.perform(get("/api/user").session(session).principal(pl))
                 .andDo(print())
                 .andExpect(status().isOk());
         
                 
                 
         
     }
}
