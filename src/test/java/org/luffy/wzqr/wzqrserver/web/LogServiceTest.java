/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import hello.IRuntimeConfig;
import hello.WebTest;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.config.MVCConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author luffy
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {MVCConfig.class, RootConfig.class, IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LogServiceTest extends WebTest {

    private OLog log;

    public LogServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Organization org = this.createUserUsb("logtestsub");
        User user = this.createUserPeople("logtestpeople", org);

        OLog ol = new OLog(user, this.request, "测试");
        ol.setMessage("测试msg");
        ol.setWho(user);
        this.log = this.logRepository.save(ol);
    }

    @After
    public void tearDown() {
        this.logRepository.delete(this.log);
        this.removeUser("logtestpeople");
        this.removeUser("logtestsub");
    }
    
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Test
    public void hello() throws Exception {
        
        for(Object obj:requestMappingHandlerAdapter.getMessageConverters()){
            System.out.println(obj);
        }
        
        Set<RequestMappingInfo> keys = requestMappingHandlerMapping.getHandlerMethods().keySet();
        
        for(RequestMappingInfo map:keys){
            System.out.println(map);
            System.out.println(requestMappingHandlerMapping.getHandlerMethods().get(map));
        }
        
        User subUser = this.userRepository.findByLoginName("logtestsub");
        MockHttpSession session = this.loginAs("logtestsub");
        
//        this.mockMvc.perform(get("/log/357")
//                .session(session)
//        )
//                .andDo(print());
        
        // log id == 357
        
        
        org.springframework.http.converter.HttpMessageNotWritableException a ;

        this.mockMvc.perform(get("/log/underlogs")
                .param("type", "测")
                .param("roleName", "报人")
                .param("time", ""+System.currentTimeMillis())
                .param("loginName", "gtestpeop")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk()) 
                .andExpect(jsonPath("$.content[0].message",is("测试msg")))
//                .andExpect(jsonPath("code", is(562)))
                ;
    }
}
