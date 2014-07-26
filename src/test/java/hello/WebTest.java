/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.Before;
import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.ApplicationRepository;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author luffy
 */
public class WebTest {
    
    @Inject
    protected WebApplicationContext context;
    @Inject
    protected ServletContext servletContext;
    @Autowired
    private FilterChainProxy springSecurityFilter;
    @Autowired
    protected MockHttpServletRequest request;
//    @Autowired
//    private FilterChainProxy springSecurityFilter;
    
    protected MockMvc mockMvc;
    @Inject
    protected PasswordEncoder passwordEncoder;
    @Inject
    protected RoleRepository roleRepository;
    @Inject
    protected LogRepository logRepository;
    /**
     * 准备建立的临时用户的正确密码
     */
    protected final String tempPassword = "1";
    /**
     * 准备建立的临时用户的错误密码
     */
    protected final String tempPassword2 = "2";
    /**
     * 准备建立的临时用户名
     */
    protected final String tempUsername = "oonoway";
    //    @Before
    //    public void setUp() {
    //    }
    //    @InjectMocks
    //            @Spy
    //    @Mock
    //    AppService appService;
    @Inject
    protected UserRepository userRepository;
    @Inject
    protected OrgRepository orgRepository;
    @Inject
    protected ApplicationRepository applicationRepository;
    
    /**
     * 通常密码
     */
    protected final String pswd = "123";
    
    
    protected Organization createUserUsb(String userName) {
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(roleRepository.findByName(Role.RoleSubManager));
        user = userRepository.save(user);

        Organization org = new Organization();
        org.setManager(user);
        org.setName(userName + "Name");
        org.setType("县市区");
        org.setSuperOrg(this.orgRepository.findByName(Organization.NameRoot));
        org = this.orgRepository.save(org);

        user.setOrg(org);
        userRepository.save(user);
        return org;
    }

    protected Organization createUserUnit(String userName, Organization superO) {
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(roleRepository.findByName(Role.RoleUnit));
        user = userRepository.save(user);

        Organization org = new Organization();
        org.setManager(user);
        org.setName(userName + "Name");
        org.setType("申报单位");
        org.setSuperOrg(superO);
        org = this.orgRepository.save(org);

        user.setOrg(org);
        userRepository.save(user);
        return org;
    }

    protected User createUserPeople(String userName, Organization superO) {
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(roleRepository.findByName(Role.RolePeople));
        user.setOrg(superO);
        return userRepository.save(user);
    }

    protected void removeUser(String userName) {
        User user = this.userRepository.findByLoginName(userName);
        if (user != null) {
            
            this.logRepository.delete(this.logRepository.findByWho(user.getId(), null));
            
            if (Role.RoleRoot.equals(user.getRole().getName())
                    || Role.RolePeople.equals(user.getRole().getName())) {
                //直接删除
                this.userRepository.delete(user);
            } else {
                Organization org = user.getOrg();
                user.setOrg(null);
                this.userRepository.save(user);

                org.setManager(null);
                this.orgRepository.save(org);

                this.userRepository.delete(user);
                this.orgRepository.delete(org);
            }
        }

    }

    protected Application createApplication(String name, Organization org, User owner) {
        Application app = new Application();
        app.setRealName(name);
        app.setRealEnglishName(name);
        app.setMyorg(org);
        app.setOwner(owner);
        app.setStatus("未上报");
        return this.applicationRepository.save(app);
    }

    protected void removeApplication(String name) {
        this.applicationRepository.delete(this.applicationRepository.findByName(name, null));
    }
    
    @Before
    public void creatMockMVC() {
//        String filterName = DEFAULT_FILTER_NAME;
//        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(filterName);
//        
//        springSecurityFilterChain.setServletContext(servletContext);
        
        MockitoAnnotations.initMocks(this);
        mockMvc = webAppContextSetup(context)
                .addFilters(springSecurityFilter)
                .build();
    }
    
    protected void saveAuth(HttpSession session){
        this.request.setSession(session);
         SecurityContext securityContext = (SecurityContext)   session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
         SecurityContextHolder.setContext(securityContext);
    }
    
    protected MockHttpSession loginAs(String userName,String password) throws Exception{
        MockHttpSession session = (MockHttpSession) this.mockMvc.perform(get("/api/user"))
//                 .andDo(print())
//                 .andExpect(status().isFound())
//                 .andExpect(redirectedUrl("http://localhost/loginPage"))
                 .andReturn().getRequest().getSession(true);
//         Redirected URL = http://localhost/login
         
         //bad password
         session  = (MockHttpSession) this.mockMvc.perform(post("/login").session(session)
                 .param("username", userName).param("password", password))
                 .andDo(print())
//                 .andExpect(status().isFound())
//                 .andExpect(redirectedUrl("/loginPage?error"))
//                 .andExpect(status().isUnauthorized())
                 .andReturn().getRequest().getSession();
//                 ;
         
//         CsrfToken token = new HttpSessionCsrfTokenRepository().loadToken(request);
         
         saveAuth(session);
         
//         this.mockMvc.perform(get("/api/user").session(session))
////                 .andDo(print())
//                 .andExpect(status().isOk());
         return session;
    }
    
    protected MockHttpSession loginAs(String userName) throws Exception{
        return this.loginAs(userName,this.pswd);
    }

    @Before
    public void createTempuser() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setLoginName(tempUsername);
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setRole(roleRepository.findByName(Role.RoleAdmin));
        userRepository.save(user);
        //        MockitoAnnotations.initMocks(this);
        //        when(userRepository.findByLoginName(same("mocku"))).thenReturn(null);
        //        when(appService.loadUserByUsername(same(tempUsername))).thenReturn(user);
    }

    @After
    public void removeTempuser() {
        userRepository.delete(userRepository.findByLoginName(tempUsername));
    }

    protected Organization createUserManager(String userName) {
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(roleRepository.findByName(Role.RoleManager));
        user = userRepository.save(user);
        Organization org = new Organization();
        org.setManager(user);
        org.setName(userName + "Name");
        org.setType("\u90e8\u95e8");
        org.setSuperOrg(this.orgRepository.findByName(Organization.NameRoot));
        org = this.orgRepository.save(org);
        user.setOrg(org);
        userRepository.save(user);
        return org;
    }

    protected void createUserRoot(String userName) {
        User user = new User();
        user.setLoginName(userName);
        user.setPassword(passwordEncoder.encode(pswd));
        user.setRole(roleRepository.findByName(Role.RoleRoot));
        user.setOrg(this.orgRepository.findByName(Organization.NameRoot));
        userRepository.save(user);
    }
    
}
