package org.annotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


//@Import({CachingConfig.class,DaoConfig.class})  
//@ComponentScan(basePackageClasses = AnalysisController.class)
//@ComponentScan(basePackageClasses = AnalysisController.class,basePackages="com.zx")
//@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = {"org.seeker.controller","org.annotation.config"},
		excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class })
})
@WebAppConfiguration()
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SpringWebConfig.class);

    /**                                                          
    * 描述 : <注册试图处理器>. <br> 
    *<p> 
    	<使用方法说明>  
     </p>                                                                                                                                                                                                                                                
    * @return                                                                                                      
    */  
    @Bean
    public ViewResolver viewResolver() {
    	logger.debug("ViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    
    
    /**
     * CommonsMultipartResolver： 使用Jakarta Commons FileUpload解析multipart请求；
		StandardServletMultipartResolver： 依赖于Servlet 3.0对multipart请求的支持
		（ 始于Spring 3.1） 。
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() { //上传类
    	CommonsMultipartResolver resolver=new CommonsMultipartResolver();
    	resolver.setDefaultEncoding("UTF-8");
    	resolver.setMaxUploadSize(-1);
    	return resolver;
    }
    @Bean
    public CookieLocaleResolver localeResolver() {
    	CookieLocaleResolver resolver=new CookieLocaleResolver();
    	return resolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor resolver=new LocaleChangeInterceptor();
    	resolver.setParamName("lang");
    	return resolver;
    }
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

//    	<!-- 避免IE在ajax请求时，返回json出现下载 <bean id="jacksonMessageConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"> <property name="supportedMediaTypes"> <list> <value>text/html;charset=UTF-8</value> </list> </property> </bean> -->
//
//    	<!-- 配置国际化拦截器 -->
//    	<!-- 拦截参数 -->
    	ReloadableResourceBundleMessageSource resolver=new ReloadableResourceBundleMessageSource();
    	resolver.setBasename("classpath:/messages/messages");
    	resolver.setDefaultEncoding("UTF-8");
    	return resolver;
    }

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		//配置静态资源处理
//		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}
    
	/**
	 * Thymeleaf配置
	 * */
	
	
    
//  为了要在Spring中使用Thymeleaf， 我们需要配置三个启用Thymeleaf与Spring集成的bean：
//  ThymeleafViewResolver： 将逻辑视图名称解析为Thymeleaf模板视图；
//  SpringTemplateEngine： 处理模板并渲染结果；
//  TemplateResolver：加载Thymeleaf模板。
//  @Bean
//  public ViewResolver viewResolver(SpringTemplateEngine engine) {//Thymeleaf视图解析器
//  	logger.debug("ViewResolver");
//  	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//  	viewResolver.setContentType("text/html; charset=utf-8"); 
//  	viewResolver.setTemplateEngine(engine);
//  	return viewResolver;
//  }
////  
//  @Bean
//  public TemplateEngine templateEngine(ITemplateResolver templateResolver) {//模版引擎
//  	logger.debug("ViewResolver");
//  	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//  	templateEngine.setTemplateResolver(templateResolver);
//  	return templateEngine;
//  }
//  @Bean
//  public ITemplateResolver templateResolver() {//模版解析器
//  	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
//       // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过<span style="font-family: Arial, Helvetica, sans-serif;">WebApplicationContext 的方法获得</span>  
//       ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());  
//       templateResolver.setPrefix("/WEB-INF/thymeleaf/");  
//       templateResolver.setSuffix(".html");  
//       // templateResolver.setCharacterEncoding("UTF-8");  
//       // 设置模板模式,也可用字符串"HTML"代替,此处不建议使用HTML5,原因看下图源码  
//       templateResolver.setTemplateMode(TemplateMode.HTML);  
//       return templateResolver;  
//  }
//  
    

}
