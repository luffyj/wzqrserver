/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.config;

import java.util.List;
import javax.xml.transform.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.data.geo.GeoModule;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.rest.webmvc.convert.UriListHttpMessageConverter;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.PagedResourcesAssemblerArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 *
 * @author luffy
 */
@Configuration
@EnableGlobalAuthentication
@ComponentScan("org.luffy.wzqr.wzqrserver.web")
//@EnableSpringDataWebSupport
@ImportResource("classpath:org/luffy/wzqr/wzqrserver/config/mvc.xml")
public class MVCConfig extends WebMvcConfigurationSupport {
    
    @Autowired
    private Environment env;
    
    /**
     * for upload
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }
        
//    @Inject
//    private ServletContextTemplateResolver templateResolver;
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(this.templateEngine());
        bean.setOrder(1);
        bean.setCharacterEncoding("UTF-8");
        return bean;
    }

//    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setTemplateResolver(this.templateResolver());
        return bean;
    }

//    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver bean = new ServletContextTemplateResolver();
        bean.setPrefix("/WEB-INF/templates/");
        bean.setSuffix(".html");
        bean.setCharacterEncoding("UTF-8");
        if(env.acceptsProfiles("dev")){
            System.out.println("Develement Mode");
            bean.setCacheable(false);
        }
//        bean.setTemplateMode("HTML5");
        return bean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/loginPage").setViewName("login");
    }

    //////////////////////以下为适配在普通mvc中 达到data rest功能
    
    
    //取消默认的messageconverter为了移除jaxb2
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);

        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());
        converters.add(new AllEncompassingFormHttpMessageConverter());

        if (ClassUtils.isPresent("com.sun.syndication.feed.WireFeed", WebMvcConfigurationSupport.class.getClassLoader())) {
            converters.add(new AtomFeedHttpMessageConverter());
            converters.add(new RssChannelHttpMessageConverter());
        }
//		if (jaxb2Present) {
//			converters.add(new Jaxb2RootElementHttpMessageConverter());
//		}
        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", WebMvcConfigurationSupport.class.getClassLoader())
                && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", WebMvcConfigurationSupport.class.getClassLoader())) {
            converters.add(new MappingJackson2HttpMessageConverter());
        } else if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", WebMvcConfigurationSupport.class.getClassLoader())
                && ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", WebMvcConfigurationSupport.class.getClassLoader())) {
            converters.add(new MappingJacksonHttpMessageConverter());
        }
        converters.add(new UriListHttpMessageConverter());
    }

    @Autowired
    private ApplicationContext context;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.web.config.SpringDataWebConfiguration#pageableResolver()
     */
//    @Bean
//    public PageableHandlerMethodArgumentResolver pageableResolver() {
//        return new PageableHandlerMethodArgumentResolver(sortResolver());
//    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.web.config.SpringDataWebConfiguration#sortResolver()
     */
//    @Bean
//    public SortHandlerMethodArgumentResolver sortResolver() {
//        return new SortHandlerMethodArgumentResolver();
//    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addFormatters(org.springframework.format.FormatterRegistry)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        if (!(registry instanceof FormattingConversionService)) {
            return;
        }

        registerDomainClassConverterFor((FormattingConversionService) registry);
    }

    private void registerDomainClassConverterFor(FormattingConversionService conversionService) {

        DomainClassConverter<FormattingConversionService> converter = new DomainClassConverter<>(
                conversionService);
        converter.setApplicationContext(context);
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addArgumentResolvers(java.util.List)
     */
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//
//        argumentResolvers.add(sortResolver());
//        argumentResolvers.add(pageableResolver());
//    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.web.config.SpringDataWebConfiguration#pageableResolver()
     */
    @Bean
//	@Override
    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {
        return new HateoasPageableHandlerMethodArgumentResolver(sortResolver());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.web.config.SpringDataWebConfiguration#sortResolver()
     */
    @Bean
//	@Override
    public HateoasSortHandlerMethodArgumentResolver sortResolver() {
        return new HateoasSortHandlerMethodArgumentResolver();
    }

    @Bean
    public PagedResourcesAssembler<?> pagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(pageableResolver(), null);
    }

    @Bean
    public PagedResourcesAssemblerArgumentResolver pagedResourcesAssemblerArgumentResolver() {
        return new PagedResourcesAssemblerArgumentResolver(pageableResolver(), null);
    }

    /* 
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addArgumentResolvers(java.util.List)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sortResolver());
        argumentResolvers.add(pageableResolver());
        argumentResolvers.add(pagedResourcesAssemblerArgumentResolver());
    }

    //jackson
    @Bean
    public GeoModule jacksonGeoModule() {
        return new GeoModule();
    }

}
