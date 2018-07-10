package org.seeker.common.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seeker.common.excel.annotation.ImportExcelTitle;
import org.seeker.common.excel.entity.ExcelMergeCell;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.seeker.entity.User;
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class VeUtil {
	private static final Logger l=LoggerFactory.getLogger(VeUtil.class);

	public static String toStringArray(Object[] a) {
		if (a == null)
			return null;
		int iMax = a.length - 1;
		if (iMax == -1)
			return null;
		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(String.valueOf(a[i]));
			if (i == iMax)
				return b.toString();
			b.append(", ");
		}
	}
	
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}


	public static Map objectToMap(Object obj) {
		if (obj == null)
			return null;
		return new BeanMap(obj);
	}
	
	/**
	 * 页面 转换参数  
	 * @param val
	 * @return
	 */
	public static String Null(String val){
		if(isNotNull(val)){
			return val;
		}
		return "";
	}
	
//    public static boolean isEmpty(String value) {
//        if (value == null || value.length() == 0) {
//            return true;
//        }
//        return false;
//    }
	
	/**
	 * 判断页面是否为null
	 * @param val
	 * @return
	 */
	public static boolean isNull(String val){
		return !isNotNull(val);
	}
	
	/**
	 * 判断页面是否不为null
	 * @param val
	 * @return
	 */
	public static boolean isNotNull(String val){
		return val!=null&&val.length()>0&&!"".equals(val.trim())&&!"null".equals(val.trim())&&!"undefined".equals(val.trim())?true:false;
	}



	/**
	 * 转换参数  Conversion
	 * @param val
	 * @return
	 */
	public static String empty(String val){
		if(isNotEmpty(val)){
			return val;
		}
		return "";
	}
	
	/**
	 * 是否为null或者空字符串
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(String val){
		return !isNotNull(val);
	}
	
	/**
	 * 是否不为null或者空字符串
	 * @param val
	 * @return
	 */
	public static boolean isNotEmpty(String val){
		return val!=null&&val.length()>0&&!"".equals(val.trim())?true:false;
	}
	
	public static boolean isEquals(String val1,String val2) {
		if(isNotEmpty(val1)&&isNotEmpty(val2)) {
			if(val1.equals(val2)) {
				return true;
			}else {
				return false;
			}
		}
		if(val1==val2) {//参数全部为null情况
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyEquals(String val1,String val2) {
		if(isNotEmpty(val1)&&isNotEmpty(val2)) {
			if(empty(val1).equals(empty(val2))) {
				return true;
			}else {
				return false;
			}
		}
		if(val1==val2) {//参数全部为null情况
			return true;
		}
		return false;
	}
	
	/**
	 * @description 去掉换行符 10：换行符 13：回车
	 * @param str
	 * @return
	 */
	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}
	
	/**
	 * 获取 Security Context 信息
	 * @return
	 */
	public static SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}
	
	/**
	 * 获取 spring security Authentication信息
	 * @return
	 */
	public static Authentication getAuthentication() {
		return getSecurityContext().getAuthentication();
	}
	
	/**
	 * 获取 spring security 帐户信息
	 * @return
	 */
	public static User getUserDeatil() {
		Object user = getAuthentication().getPrincipal();
		if (user instanceof User) {
			return (User) user;
		} else {
			throw new AccessDeniedException("用户不存在");
		}
	}
	
//	public static WebAuthenticationDetails getWebAuthenticationDetail() {
//		Object webDetail = getAuthentication().getDetails();
//		if (webDetail instanceof WebAuthenticationDetails) {
//			return (WebAuthenticationDetails) webDetail;
//		} else {
//			throw new AccessDeniedException("session信息不存在");
//		}
//	}
	
	/**
	 * 获取权限信息
	 * @return
	 */
//	public static Set<AuthorityInfo> getAuthorityInfos(){
//		return getUserDeatil().getAuthorities();
//	}
	
	private static final DecimalFormat dfs = new DecimalFormat("0000");
	private static final DecimalFormat dfs_20 = new DecimalFormat("00000000000000000000");
	
	/**
	 * 获取权限信息
	 * @return
	 */
	public static String fomatNumber(String val){
		try {
			l.debug(val);
			int num=Integer.valueOf(val);
			return dfs.format(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return dfs.format(0);
		}
	}
	
	/**
	 * 获取权限信息
	 * @return
	 */
	public static String fomatNumber_20(String val){
		try {
			l.debug(val);
			BigInteger num=new BigInteger(val);
			return dfs_20.format(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return dfs_20.format(0);
		}
	}
	

	public static boolean isNotNullList(List list) {
		return null!=list?true:false;
	}
	public static boolean isNotEmptyList(List list) {
		return null!=list&&!list.isEmpty()?true:false;
	}
	public static boolean isNotNullArray(Object[] val) {
		return null!=val?true:false;
	}
	public static boolean isNotEmptyArray(Object[] val) {
		return null!=val&&val.length!=0?true:false;
	}



	public static boolean isNullList(List list) {
		return !isNotNullList(list);
	}
	public static boolean isEmptyList(List list) {
		return !isNotEmptyList(list);
	}
	public static boolean isNullArray(Object[] val) {
		return !isNotNullArray(val);
	}
	public static boolean isEmptyArray(Object[] val) {
		return !isNotEmptyArray(val);
	}
	
	//首字母大写
	public static String captureName(String name) {
	 	if(null==name||"".equals(name)){
	 		return "";
	 	}
	 	return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	public static String handleFileNameByURL(String url) {
		l.debug(url);
		String fileName="";
		int i=url.lastIndexOf(File.separator);
//		int i=url.lastIndexOf("\\");
//		if(i==-1) {
//			i=url.lastIndexOf("/");
//		}
		fileName=url.substring(i+1);
		l.debug(fileName);
		return fileName;
	}
	 
	
	
	

	/**
	 * 将excelPo属性转换成与Excel对应列名称
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public static Map getMapByClazz(Class pojoClass) throws Exception{
		Map<String, Map<String, Method>> map = new HashMap<String, Map<String, Method>>();//获得excelPo里面添加属性值的方法
		Field filed[] = pojoClass.getDeclaredFields();
		Map<String, Method> fieldSetMap = new HashMap<String, Method>();//获得excelPo里面添加属性值的方法
		Map<String, Method> fieldSetConvertMap = new HashMap<String, Method>();
		for (int i = 0; i < filed.length; i++) {
			Field f = filed[i];
			ImportExcelTitle excel = f.getAnnotation(ImportExcelTitle.class);
			if (excel != null) {
				String fieldname = f.getName();
				String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
				Method setMethod = pojoClass.getMethod(setMethodName, new Class[] { f.getType() });
				fieldSetMap.put(excel.exportName(), setMethod);
				if (excel.importConvertSign() == 1) {
					StringBuffer setConvertMethodName = new StringBuffer("convertSet");
					setConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
					setConvertMethodName.append(fieldname.substring(1));
					Method getConvertMethod = pojoClass.getMethod(setConvertMethodName.toString(), new Class[] { String.class });
					fieldSetConvertMap.put(excel.exportName(), getConvertMethod);
				}
			}
		}
		map.put("fieldSetConvertMap",fieldSetConvertMap);
		map.put("fieldSetMap",fieldSetMap);
		return map;
	}


	/**
	 * 获取类的属性封装成map集合返回
	 * @param clazz
	 * @return
	 */
	public static Map<String, Field> getMapClassField(Class clazz) {
		Field[] fields=clazz.getDeclaredFields();
		Map<String,Field> map=new HashMap<>();
		for(int i=0;i<fields.length;i++){
			fields[i].setAccessible(true);
			l.debug(fields[i].getName());
			map.put(fields[i].getName(),fields[i]);
		}
		return map;
	}


	/**
	 * 获取该类全部的方法 返回一个map集合  key  方法名称 值为 方法对象  因为方法可以重载所以可能方法名相同
	 * 方法名称需要做单独处理 handleClassMethodName
	 * 以应对bean对象属性中大小写问题
	 * @param clazz
	 * @return
	 */
	public static Map<String,List<Method>> getMapClassMethods(Class clazz){
		Method[] ms=clazz.getDeclaredMethods();//拿到该类所有的方法,不包括继承的类
		Map<String,List<Method>> mMap=new HashMap<>();
		List<Method> list=null;
		String methodName="";
		for (int i = 0; i < ms.length; i++) {
			methodName=handleClassMethodName(ms[i].getName());
			l.debug(methodName);
			list=mMap.get(methodName);
			if(VeUtil.isNullList(list)){
				list=new ArrayList<>();
			}
			list.add(ms[i]);
			mMap.put(methodName,list);
		}
		return mMap;
	}

	/**
	 * 通过传入类和属性名称 获取 属性的相关信息
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getClassFieldByName(Class clazz,String fieldName) {
		Map<String, Field> map = getMapClassField(clazz);
		return map.get(fieldName);
	}

	/**
	 * 通过传入类和方法名称 获取方法集合
	 * @param clazz
	 * @param methodName
	 * @return
	 */
	public static List<Method> getClassMethodByName(Class clazz,String methodName){
		Map<String, List<Method>> map = getMapClassMethods(clazz);
		return map.get(handleClassMethodName(methodName));
	}
	private static String handleClassMethodName(String val){
		return val.toLowerCase();
	}


	/**
	 * 获取对象中的get方法
	 * @param clazz
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Method getClassGetMethodByName(Class clazz,String name) throws Exception {
		List<Method> list = getClassMethodByName(clazz, "get"+name);
		Field f=getClassFieldByName(clazz,name);
		for (int i = 0; i <list.size() ; i++) {
			Method m=list.get(i);
			if(m.getReturnType().equals(f.getType())&&m.getGenericParameterTypes().length==0){
				return m;
			}
		}
		throw new Exception("传入的数据有问题 : "+name+" Class : "+clazz.getClass().getName());
	}

	/**
	 * 获取类对象中属性上的注解信息
	 * @param clazz
	 * @return
	 */
	public static Map<String,Object> getMapClassFieldAnnotation(Class clazz,Class annotationClazz){
		Map<String,Field> fieldMap=getMapClassField(clazz);
		Map<String,Object> map=new HashMap<>();
		Field f=null;
		for (String key: fieldMap.keySet()) {
			f=fieldMap.get(key);
			if(null==f){
				continue;
			}
			map.put(key,f.getAnnotation(annotationClazz));
		}
		return map;
	}







	public static void main(String[] args) throws Exception {
		Method val = getClassGetMethodByName(ExcelMergeCell.class, "val");
		System.out.println(val);


	}



}
