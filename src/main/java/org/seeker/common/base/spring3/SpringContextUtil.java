package org.seeker.common.base.spring3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
//@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.INTERFACES)//
//@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.TARGET_CLASS)//
@SuppressWarnings({"unused","static-access","unchecked","rawtypes"})
public class SpringContextUtil implements ApplicationContextAware {
	private static Logger logger=LoggerFactory.getLogger(SpringContextUtil.class);

	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
//		 String[] str=applicationContext.getBeanDefinitionNames();
//		 logger.debug("-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
//		 for (int i = 0; i < str.length; i++) {
//			 logger.debug(str[i]);
//		 }
//		 Environment  e= applicationContext.getEnvironment();//
//		 String[] es=e.getDefaultProfiles();
//		 String[] es=e.getActiveProfiles();
//		 e.
//		 for (int i = 0; i < es.length; i++) {
//			 logger.debug(es[i]);
//		}

	}

	private static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static Object getBean(String name, Class requiredType) {
		return getApplicationContext().getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return getApplicationContext().containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return getApplicationContext().isSingleton(name);
	}

	public static Class getType(String name) {
		return getApplicationContext().getType(name);
	}

	public static String[] getAliases(String name) {
		return getApplicationContext().getAliases(name);
	}

}
