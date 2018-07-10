package org.seeker.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({"rawtypes","unchecked"})
public class JsonUtil {
	private static Logger logger=LoggerFactory.getLogger(JsonUtil.class);

	public static String toJSONStringByFastjson(List list) {
		String result= JSONArray.toJSONString(list);
		logger.debug(result);
		return result;
	}
	
	/**
	 * 获取对象json串
	 * @param param
	 * @return
	 */
	public static String toJSONStringByFastjson(Object param) {
		String result=JSONArray.toJSONString(param);
		logger.debug(result);
		return result;
	}
	/**
	 * 获取对象json串
	 * @param param
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String toJSONStringByJackjson(Object param) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String result=mapper.writeValueAsString(param);
		logger.debug(result);
		return result;
	}



	private static String toJson(Map map){
		return JSON.toJSONString(map);
	}



	public static String toJSONStringByFastjson(PageInfo data) {
		Map jsonMap = new HashMap();
		jsonMap.put("total", Long.valueOf(data.getTotal()));
		jsonMap.put("rows", data.getList());
		String result=toJson(jsonMap);
		logger.debug(result);
		return result;
	}

	/**
	 * 获取分页json串
	 * @param total
	 * @param rows
	 * @return
	 */

	public static String toJSONStringByFastjson(int total, List rows) {
		Map jsonMap = new HashMap();
		jsonMap.put("total", Integer.valueOf(total));
		jsonMap.put("rows", rows);
		String result=toJson(jsonMap);
		logger.debug(result);
		return result;
	}
	

}
