/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.inject.Inject;
import org.luffy.lib.libspring.config.RuntimeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;

/**
 *
 * @author luffy
 */
@Configuration
public class IRuntimeConfig implements RuntimeConfig{
    
    @Inject
    private JpaDialect jpaDialect;
    @Inject
    private Environment env;
    
    
    @Override
    @Bean
    public EntityManagerFactoryInfo entityManagerFactory() {
        LocalEntityManagerFactoryBean bean = new LocalEntityManagerFactoryBean();
        bean.setPersistenceUnitName("wzqrpu_deve_mysql");
        bean.setJpaDialect(jpaDialect);
        return bean;
    }
    
    @Bean
    @Override
    public JpaDialect jpaDialect(){
        return new EclipseLinkJpaDialect();
    }
}
