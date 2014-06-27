/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import org.luffy.lib.libspring.loader.MutliDispatcherLoader;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.luffy.wzqr.wzqrserver.config.MVCConfig;
import org.luffy.wzqr.wzqrserver.config.ProductionConfig;
import org.luffy.wzqr.wzqrserver.config.RestDataConfig;
import org.luffy.wzqr.wzqrserver.config.RootConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 *
 * @author luffy
 */
public class WzqrLoader extends MutliDispatcherLoader {

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        rootAppContext.register(new Class[]{RootConfig.class, ProductionConfig.class});
        return rootAppContext;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        
        String filterName = DEFAULT_FILTER_NAME;
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(filterName);
//        String contextAttribute = getWebApplicationContextAttribute();
//        if(contextAttribute != null) {
//            springSecurityFilterChain.setContextAttribute(contextAttribute);
//        }
        registerFilter(servletContext, true, filterName, springSecurityFilterChain);
        
        registerDispatcherServlet(servletContext,
                "restDataDispatcher",
                new Class[]{RestDataConfig.class}, 2, new String[]{"/api/*"},
                true, null);
        
        registerDispatcherServlet(servletContext,
                "dispatcher",
                new Class[]{MVCConfig.class}, 1, new String[]{"/*"},
                true, null);
    }
    
     /**
     * Registers the provided filter using the {@link #isAsyncSecuritySupported()} and {@link #getSecurityDispatcherTypes()}.
     *
     * @param servletContext
     * @param insertBeforeOtherFilters should this Filter be inserted before or after other {@link Filter}
     * @param filterName
     * @param filter
     */
    private void registerFilter(ServletContext servletContext, boolean insertBeforeOtherFilters, String filterName, Filter filter) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if(registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for '" + filterName +"'. Check to ensure the Filter is only configured once.");
        }
        registration.setAsyncSupported(true);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
        registration.addMappingForUrlPatterns(dispatcherTypes, !insertBeforeOtherFilters, "/*");
    }

}
