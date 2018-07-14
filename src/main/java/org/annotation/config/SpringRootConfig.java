package org.annotation.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//<context:component-scan base-package="com.seeker">  
//<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>  
//</context:component-scan>  
//<import resource="classpath:config/context/applicationContext-CachingConfig.xml" />
//<import resource="classpath:config/context/applicationContext-DaoConfig.xml" /> 
//@Import({DaoConfig.class,DaoConfig.class})  


//@EnableAspectJAutoProxy(proxyTargetClass=true)  
//@ActiveProfiles("dev")
//@ComponentScan(
//		basePackages = {"com.seeker"}, 
//		excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class })
//		})  
//@Import({DataSourceConfig.class})  

//@ComponentScan(
//		basePackages = {"com.seeker"}
//		)  
@ImportResource("classpath:spring/spring.xml")
@Configuration
@PropertySource({"classpath:/log4j.properties"})
@Controller
public class SpringRootConfig {

	public SpringRootConfig() {
		super();
		System.out.println("SpringRootConfig");
	}
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "forward:/demo/index.jsp";
	}
	

}
