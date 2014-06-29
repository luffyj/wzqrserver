/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

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
    private Filter springSecurityFilter;
    @Autowired
    protected MockHttpServletRequest request;
//    @Autowired
//    private FilterChainProxy springSecurityFilter;
    
    protected MockMvc mockMvc;
    
    @Before
    public void setUp() {
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
    
}
