/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import hello.IRuntimeConfig;
import hello.WebTest;
import java.security.Principal;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luffy.wzqr.wzqrserver.beans.AppService;
import org.luffy.wzqr.wzqrserver.beans.bean.JsonResponse;
import org.luffy.wzqr.wzqrserver.config.MVCConfig;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.OLog;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luffy
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {MVCConfig.class, RootConfig.class, IRuntimeConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationServiceTest extends WebTest {

    public ApplicationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private final String userRoot = "iroot1";
    private final String userManager = "imanager1";
    private final String userSub1 = "isub1";
    private final String userSub2 = "isub2";
    private final String userUnit1 = "iunit1";
    private final String userUnit2 = "iunit2";
    private final String userPeople1 = "ipeople1";//unit1 初始就有app
    private final String userPeople2 = "ipeople2";//unit1
    private final String userPeople3 = "ipeople3";//unit2
    private final String userPeople4 = "ipeople4";//不存在
    private final String app1 = "iapp1";
    private final String app2 = "iapp1";

    @Before
    public void setUp() {
        createUserRoot(userRoot);
        Organization org;
        createUserManager(userManager);

        Organization unitorg = createUserUsb(userSub1);
        createUserUsb(userSub2);

        org = createUserUnit(userUnit1, unitorg);
        User user = createUserPeople(userPeople1, org);
        createUserPeople(userPeople2, org);

        createApplication(app1, org, user);
        createApplication(app2, org, null);

        org = createUserUnit(userUnit2, unitorg);
        createUserPeople(userPeople3, org);

    }

    @After
    public void tearDown() {
        removeApplication(app1);
        removeApplication(app2);

        removeUser(userPeople1);
        removeUser(userPeople2);
        removeUser(userPeople3);
        removeUser(userPeople4);
        removeUser(userUnit2);
        removeUser(userUnit1);
        removeUser(userSub2);
        removeUser(userSub1);
        removeUser(userManager);
        removeUser(userRoot);
    }

    /**
     * Test of upload method, of class ApplicationService.
     */
    @Test
    public void testUpload() throws Exception {
        //上传还不知道怎么弄
    }

    /**
     * Test of downloadPDF method, of class ApplicationService.
     */
    @Test
    public void testDownloadPDF() {

    }

    /**
     * Test of changeOwner method, of class ApplicationService.
     */
    @Test
    public void testChangeOwner() throws Exception {

        this.mockMvc.perform(post("/cappowner")
        )
                //                 .andDo(print())
                .andExpect(status().isFound());

        // 登录2 执行应该失败
        MockHttpSession session = this.loginAs(this.userUnit2);

        this.mockMvc.perform(post("/cappowner")
                .session(session))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Application tapp2 = this.applicationRepository.findByName(this.app2, null).getContent().get(0);

        //////只有申报单位才可以
        session = this.loginAs(this.userRoot);

        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(582)));

        session = this.loginAs(this.userManager);
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(582)));

        session = this.loginAs(this.userSub1);
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(582)));

        session = this.loginAs(this.userPeople1);
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(582)));

        //////end
        session = this.loginAs(this.userUnit2);

        //不是unit2的app 非管辖范围
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(582)));

        session = this.loginAs(this.userUnit1);

        //people3已经存在
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople3)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(585)));

        //用户2已存在的 无需提供密码
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople2)
                .param("password", "")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(200)));

        assertEquals(this.userPeople2, this.applicationRepository.findOne(tapp2.getId()).getOwner().getLoginName());

        //需要密码
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople4)
                .param("password", "")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(583)));

        //可以自动新增
        this.mockMvc.perform(post("/cappowner")
                .param("appid", "" + tapp2.getId())
                .param("username", this.userPeople4)
                .param("password", this.pswd)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(200)));

        assertEquals(this.userPeople4, this.applicationRepository.findOne(tapp2.getId()).getOwner().getLoginName());

    }

    @Test
    public void submit() throws Exception {
        this.mockMvc.perform(post("/submitapp")
        )
                //                 .andDo(print())
                .andExpect(status().isFound());

        // 登录2 执行应该失败
        MockHttpSession session = this.loginAs(this.userUnit2);

        this.mockMvc.perform(post("/submitapp")
                .session(session))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Application tapp2 = this.applicationRepository.findByName(this.app2, null).getContent().get(0);

        //////只有申报单位才可以
        session = this.loginAs(this.userRoot);

        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(562)));

        session = this.loginAs(this.userManager);
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(562)));

        session = this.loginAs(this.userSub1);
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(562)));

        session = this.loginAs(this.userPeople1);
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(562)));

        //////end
        session = this.loginAs(this.userUnit2);

        //不是unit2的app 非管辖范围
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(563)));

        session = this.loginAs(this.userUnit1);

        //不是unit2的app 非管辖范围
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(200)));

        assertEquals("等待形审", this.applicationRepository.findOne(tapp2.getId()).getStatus());

        //再上报一次
        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(564)));

        //改至任意的退回应该也是可以的
        tapp2 = this.applicationRepository.findOne(tapp2.getId());

        tapp2.setStatus("任意的退回哦");
        this.applicationRepository.save(tapp2);

        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + tapp2.getId())
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(200)));

        assertEquals("等待形审", this.applicationRepository.findOne(tapp2.getId()).getStatus());
        
        System.out.println("log");
        for(OLog log:this.logRepository.findAll()){
            System.out.println(log);
        }
    }

    /**
     * Test of approval method, of class ApplicationService.
     */
    @Test
    public void testApproval() throws Exception {
        //不再判断权限了 好累……
        this.mockMvc.perform(post("/approvalapp")
        )
                //                 .andDo(print())
                .andExpect(status().isFound());

        Long appid = this.applicationRepository.findByName(this.app1, null).getContent().get(0).getId();

        MockHttpSession session = this.loginAs(this.userRoot);

        this.mockMvc.perform(post("/approvalapp")
                .session(session))
                .andDo(print())
                .andExpect(status().isBadRequest());
        
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-2")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(571)));
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "2")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(571)));
        
        //状态
        
        //需要先上报
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(575)));
        
        simpleSubmit(appid);        
        //ok继续
        
        //权限
//        session = this.loginAs(this.userRoot);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session = this.loginAs(this.userManager);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session = this.loginAs(this.userPeople1);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session = this.loginAs(this.userUnit1);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        //初审的特有检查
        session = this.loginAs(this.userSub2);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(574)));
        
        session = this.loginAs(this.userSub1);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("形审退回", this.applicationRepository.findOne(appid).getStatus());
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(575)));
        
        simpleSubmit(appid);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("形审未过", this.applicationRepository.findOne(appid).getStatus());
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("形审通过", this.applicationRepository.findOne(appid).getStatus());
        
        ////////////////////////////
        //开始复审 只有管理员才可以而部门管理员 只可以退回
        //current sub
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session  = this.loginAs(this.userPeople1);
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        session  = this.loginAs(this.userUnit1);
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session  = this.loginAs(this.userManager);
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("复审退回", this.applicationRepository.findOne(appid).getStatus());
        
        simplePassXingshen(appid);
        
        session  = this.loginAs(this.userRoot);
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("复审退回", this.applicationRepository.findOne(appid).getStatus());
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "-1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(575)));
        
        simplePassXingshen(appid);
        
        session  = this.loginAs(this.userManager);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session  = this.loginAs(this.userRoot);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("复审未过", this.applicationRepository.findOne(appid).getStatus());
        
        
        session  = this.loginAs(this.userManager);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session  = this.loginAs(this.userRoot);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("复审通过", this.applicationRepository.findOne(appid).getStatus());
        
        
        session  = this.loginAs(this.userManager);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(573)));
        
        session  = this.loginAs(this.userRoot);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("评审未过", this.applicationRepository.findOne(appid).getStatus());
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("评审通过", this.applicationRepository.findOne(appid).getStatus());
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "0")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("评审未过", this.applicationRepository.findOne(appid).getStatus());
        
        
    }

    private void simpleSubmit(Long appid) throws Exception {
        MockHttpSession session = this.loginAs(this.userUnit1);

        this.mockMvc.perform(post("/submitapp")
                .param("appid", "" + appid)
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code", is(200)));
        
        assertEquals("等待形审", this.applicationRepository.findOne(appid).getStatus());
    }

    private void simplePassXingshen(Long appid) throws Exception {
        simpleSubmit(appid);
        
        MockHttpSession session = this.loginAs(this.userSub1);
        
        this.mockMvc.perform(post("/approvalapp")
                .param("appid", ""+appid)
                .param("reason", "r")
                .param("result", "1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code",is(200)));
        
        assertEquals("形审通过", this.applicationRepository.findOne(appid).getStatus());
    }

}
