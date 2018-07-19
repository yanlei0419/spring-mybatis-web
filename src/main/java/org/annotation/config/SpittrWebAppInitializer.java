package org.annotation.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * servlet 3.0   的 web.xml
 * spring mvc 配置
 */
//@WebFilter(servletNames = {"SimpleServlet"},filterName="SimpleFilter")
//@WebListener("This is only a demo listener")
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定 Root WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringRootConfig.class};
    }

    @Override
	public void onStartup(ServletContext sc) throws ServletException {//加载servlet

//			sc.addListener(SpringEncodingFilter);
//			sc.addServlet(servletName, servletClass)
    	super.onStartup(sc);
	}

	/**
     * 指定 Servlet WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringWebConfig.class};
    }

    /**
     * 指定 Servlet mappings
     * 第一个方法是getServletMappings()， 它会将一个或多个路径映射
		到DispatcherServlet上。 在本例中， 它映射的是“/”， 这表示它会是应用的默认
		Servlet。 它会处理进入应用的所有请求。
		为了理解其他的两个方法， 我们首先要理解DispatcherServlet和一个Servlet监听器（ 也
		就是ContextLoaderListener） 的关系。
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter SpringEncodingFilter=new CharacterEncodingFilter();
        SpringEncodingFilter.setEncoding("UTF-8");
        SpringEncodingFilter.setForceEncoding(true);


        return new Filter[]{SpringEncodingFilter};
    }
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("home");
//    }

    /**
//    借助customizeRegistration()方法中的ServletRegistration.Dynamic， 我们能
//    够完成多项任务， 包括通过调用setLoadOnStartup()设置load-on-startup优先级， 通过
//    setInitParameter()设置初始化参数， 通过调用setMultipartConfig()配置Servlet
//    3.0对multipart的支持。 在前面的样例中， 我们设置了对multipart的支持， 将上传文件的临时
//    存储目录设置在“tmpspittr/uploads”中。
 * */
}