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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.beans.AppService;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.ApplicationRepository;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * a good example https://github.com/e-ivaldi/easy-bank/blob/dev/src/test/java/com/manuv/test/controller/MvcTest.java
 * @author luffy
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {RestDataConfig.class,RootConfig.class,IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class APITest extends WebTest{
    
    public APITest() {
    }
    
    @Inject
    protected ApplicationRepository applicationRepository;
    @Inject
    protected OrgRepository orgRepository;
    
    @Inject
    protected UserRepository userRepository;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
        
    @Test
    public void application(){
        Application app = new Application();
        app.setAddress("随意");
        long count = applicationRepository.count();
        applicationRepository.save(app);        
        assertEquals(count+1,applicationRepository.count());
        applicationRepository.delete(app);
        assertEquals(count,applicationRepository.count());
        
        
        Organization root = orgRepository.findByName(Organization.NameRoot);
        count = applicationRepository.findBySuperOrg(root.getId(), null).getTotalElements();
        
        app = new Application();
        app.setAddress("随意");   
        app.setMyorg(root);
        applicationRepository.save(app);
        try{
            assertEquals(count+1,applicationRepository.findBySuperOrg(root.getId(), null).getTotalElements());
        }finally{
            applicationRepository.delete(app);
        }
        
        User user = userRepository.findAll().iterator().next();
        
        
        count = applicationRepository.findByOwner(user.getId(), null).getTotalElements();
        
        app = new Application();
        app.setAddress("随意");   
        app.setOwner(user);
//        app.setMyorg(root);
       
        applicationRepository.save(app);
        try{
            assertEquals(count+1,applicationRepository.findByOwner(user.getId(), null).getTotalElements());
        }finally{
            applicationRepository.delete(app);
        }
    }
    
    @Test
     public void adminLogin() throws Exception {
         this.mockMvc.perform(post("/login")
                 .param("username", "admin").param("password", "admin"))
                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("/"))
//                 .andExpect(status().isUnauthorized())
                 .andReturn().getRequest();
     }
    
     @Test
     public void login() throws Exception {
                
         
         assertTrue(true);
         MockHttpSession session = (MockHttpSession) this.mockMvc.perform(get("/api/user"))
//                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("http://localhost/loginPage"))
                 .andReturn().getRequest().getSession();
//         Redirected URL = http://localhost/login
         
         //bad password
         MockHttpServletRequest request = this.mockMvc.perform(post("/login").session(session)
                 .param("username", tempUsername).param("password", tempPassword2))
//                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("/loginPage?error"))
//                 .andExpect(status().isUnauthorized())
                 .andReturn().getRequest();
//                 ;
         
//         CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
         
         session = (MockHttpSession) this.mockMvc.perform(post("/login").session(session)
                 .param("username", tempUsername).param("password", tempPassword)
//                 .param(token.getParameterName(), token.getToken())
                    )
//                 .andDo(print())
                 .andExpect(status().isFound())
                 .andExpect(redirectedUrl("http://localhost/api/user"))
                 .andReturn().getRequest().getSession();
//                 .andReturn().getRequest().getSession();
         saveAuth(session);
         
         this.mockMvc.perform(get("/api/user").session(session))
//                 .andDo(print())
                 .andExpect(status().isOk());
         
                 
                 
         
     }
}
