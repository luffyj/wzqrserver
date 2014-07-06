/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver;

import hello.IRuntimeConfig;
import hello.WebTest;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.config.MVCConfig;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
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
@ContextConfiguration(classes = {MVCConfig.class,RootConfig.class,IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class MVCTest extends WebTest{
    
    public MVCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
     @Test
     public void queryUser() throws Exception{
//         User u = userRepository.findByLoginName(tempUsername);
         MockHttpSession session = (MockHttpSession) this.request.getSession(true);
         
         this.mockMvc.perform(get("/queryUser")
         .session(session))
                 .andDo(print())
                 ;
         
         MockHttpServletRequest request = this.mockMvc.perform(get("/api/user")
         .session(session)
         )                 
                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("http://localhost/loginPage"))
                 .andReturn().getRequest();
         
         request = this.mockMvc.perform(post("/login").session(session))
                 .andReturn().getRequest();
         
//         CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
         
//         this.mockMvc.perform(get("/login").session(session))
//                 .andDo(print())
//                 .andExpect(status().isOk());
         
         session = (MockHttpSession) this.mockMvc.perform(post("/login").session(session)
                 .param("username", tempUsername).param("password", tempPassword)
//                 .param(token.getParameterName(), token.getToken())
                    )
                 .andDo(print())
                 .andExpect(status().isFound())
//                 .andExpect(redirectedUrl("http://localhost/api/user"))
                 .andReturn().getRequest().getSession();
         
         this.mockMvc.perform(get("/queryUser")
         .session(session))
                 .andDo(print())
                 ;
         
     }
}
