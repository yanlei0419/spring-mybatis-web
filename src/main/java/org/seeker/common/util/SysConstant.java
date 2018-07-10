package org.seeker.common.util;


import org.seeker.common.config.SysConfig;

import java.util.HashMap;
import java.util.Map;

public class SysConstant {

	public final static String SYSTEM_NAME 					= "探索";// 添加
	public final static String SQL_ADD 						= "1";// 添加
	public final static String SQL_DELETE 					= "2";// 删除
	public final static String SQL_UPDATE 					= "3";// 修改
	public final static String SQL_SELECT 					= "4";// 查询
	public final static String EXCEL 						= "5";// 导出
	public final static String SQL_BATCH_ADD 				= "6";// 批量添加
	public final static String LOGOUT 						= "7";
	public final static String SQL_DISABLE 					= "8";


	public final static String SYSTEM 						= "系统模块";


	public final static String log4jPath 				= SysConfig.get("log4j.path");// log4j 保存路径
	public final static String uploadPath 				= SysConfig.get("upload.path");// 上传保存路径

	public final static String initPassWord 			= SysConfig.get("initPassWord");// 初始化密码


	public final static String sessionUser 				= "User";
	public final static String key 						= "vegetto";
	public final static String SA 						= "7d95ef1d8084e79e128ec9ec6c07812c";
	public final static String SPW 						= "04a79f7a06bfa64500852cd55fd36ebd";
	public static final String OBJECT_STREAM_PATH 		= SysConfig.get("object.stream.path");
	public final static String DOWNLOAD 				= "download";
	public final static String DOWNLOAD_ZIP 			= "zip";
	/**
	 * 分隔符
	 */
	public final static String sign 					= "--";

	public final static Map<String,String> map=new HashMap<String,String>(){//匿名内部类
		{
			put("vegetto","GoKu+Vegata");
		}
	};
	

}
