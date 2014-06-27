/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.config;

import javax.inject.Inject;
import org.luffy.lib.libspring.config.RuntimeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;

/**
 *
 * @author luffy
 */
@Configuration
public class ProductionConfig implements RuntimeConfig{
    
    @Inject
    private JpaDialect jpaDialect;

    @Override
    @Bean
    public EntityManagerFactoryInfo entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaDialect(jpaDialect);
        bean.setPersistenceUnitName("wzqrpu_life");
        
        /*forbid the weaving
        for enable it
        step 1 
        <property name="loadTimeWeaver">
        <bean class="org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver"/>\
        </property>
        
        to application beans
        
        step 2 Then add this option to your JVM :
        -javaagent:/path-to-your-javaagent/org.springframework.instrument-3.1.1.RELEASE.jar
        
        In Spring 3.x the javaagent is localized in the org.springframework.instrument jar.
        You need the org.springframework.instrument library together with aspectjrj.jar & aspectjweaver.jar librairies.
        
                */
        bean.getJpaPropertyMap().put("eclipselink.weaving", "false");
        return bean;
    }

    @Override
    @Bean
    public JpaDialect jpaDialect() {
        return new EclipseLinkJpaDialect();
    }
    
}
