package org.seeker.common.config;

import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// 读取 系统配置文件properties

public class SysConfig extends Properties {
	private static final long serialVersionUID = 1L;
	private static Logger l = LoggerFactory.getLogger(SysConfig.class);
	private SysConfig() {}

	private static SysConfig prop=new SysConfig();
	/**
	 * 装载配置信息
	 * @author yanlei
	 */
	static{
		init("/properties/initConfig.properties");
	}

	/**
	 * 初始化文件名配置信息
	 * @param cfgFilePath
	 */
	private static void init(String cfgFilePath) {
		try {
			InputStream in = SysConfig.class.getResourceAsStream(cfgFilePath);
			prop.load(in);
			in.close();
//			l.debug(prop.toString());
		} catch (Exception e) {
			l.error(e.getMessage());
		}
	}

	/**
	 * 获取Properties对象配置信息
	 * @return
	 */
	public static Properties getProp() {
		return prop;
	}

	/**
	 * 通过key获取配置信息
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return prop.getProperty(key);
	}

}
