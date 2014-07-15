/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 *
 * @author luffy
 */
@Configuration
@ComponentScan("org.luffy.wzqr.wzqrserver.rest")
public class RestDataConfig extends RepositoryRestMvcConfiguration{
    
    @Autowired
    private Environment environment;

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        if(environment.acceptsProfiles("test"))
            config.setBaseUri(URI.create("api/"));
    }

    @Override
    protected void configureExceptionHandlerExceptionResolver(ExceptionHandlerExceptionResolver exceptionResolver) {
        super.configureExceptionHandlerExceptionResolver(exceptionResolver);
    }
    
}
