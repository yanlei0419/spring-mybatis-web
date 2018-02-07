package com.seeker.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		String result=JSON.toJSONString(jsonMap);
		logger.debug(result);
		return result;
	}
	/**
	 * 获取分页json串
	 * @param total
	 * @param rows
	 * @return
	 */
	public static String toJSONStringByFastjson(int total, List rows,List sel) {
		Map jsonMap = new HashMap();
		jsonMap.put("total", Integer.valueOf(total));
		jsonMap.put("rows", rows);
		// 测试数据
//		sel=new ArrayList<String>();
//		sel.add("codecode");
//		sel.add("col1col1");
//		sel.add("上传人create_bys");
		jsonMap.put("sel",sel);
		String result=JSON.toJSONString(jsonMap);
		logger.debug(result);
		return result;
	}
	public static String toJSONStringByFastjson(int total, Collection rows) {
		Map jsonMap = new HashMap();
		jsonMap.put("total", Integer.valueOf(total));
		jsonMap.put("rows", rows);
		String result=JSON.toJSONString(jsonMap);
		logger.debug(result);
		return result;
	}
	

}
