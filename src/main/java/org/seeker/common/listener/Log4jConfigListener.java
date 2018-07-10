package org.seeker.common.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Log4jConfigListener implements ServletContextListener {
	@SuppressWarnings("unused")
	private static Logger l=LoggerFactory.getLogger(Log4jConfigListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/*
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		String path = arg0.getServletContext().getRealPath("");
//		l.debug("path>>>>>>>>"+path);
//		String temp=path.substring(0,path.lastIndexOf(File.separatorChar));
//		l.debug("temp>>>>>>>>"+temp);
//		String logBasePath=temp.substring(0,temp.lastIndexOf(File.separatorChar)+1);
//		l.debug("logBasePath>>>>>>>>"+logBasePath);//服务器根目录
//		String log4jPath =logBasePath+ SysConstant.log4jPath ;
//		log4jPath=log4jPath.replace('/', File.separatorChar);
//		log4jPath=log4jPath.replace('\\', File.separatorChar);
//		l.debug("log4jPath>>>>>>>>"+log4jPath);
	}

}
