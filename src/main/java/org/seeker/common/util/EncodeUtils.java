package org.seeker.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class EncodeUtils {
	private static final Logger l=LoggerFactory.getLogger(EncodeUtils.class);
	private static final Md5PasswordEncoder md5=new Md5PasswordEncoder();
	
	
	public static String EncoderByMd5(String val) {
		if(null==val||"".equals(val)){return "";}
    	String encodeVal = md5.encodePassword(val,SysConstant.key);
    	l.debug(val+">>>>>"+encodeVal);
    	return encodeVal;
	}
	
	public static String EncoderByMd5(CharSequence val) {
		if(null==val||"".equals(val)){return "";}
    	String encodeVal = md5.encodePassword(val.toString(),SysConstant.key);
    	l.debug(val+">>>>>"+encodeVal);
    	return encodeVal;
	}
	
	/**
	 * @param encodedPassword 数据库
	 * @param val 用户提交
	 * @return
	 */
	public static boolean isPasswordValid(String encodedPassword, String val) {
		return md5.isPasswordValid(encodedPassword, val, SysConstant.key);
	}
	
//	public static String EncoderByMd5_2(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//		if(null==str||"".equals(str)){
//			return "";
//		}
//		//确定计算方法
//		MessageDigest md5=MessageDigest.getInstance("MD5");
//		BASE64Encoder base64en = new BASE64Encoder();
//		//加密后的字符串
//		String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
//		return newstr;
//	}
	
	public static void main(String[] args) {
		while(true){
			EncoderByMd5(Uuid.getUUID());
//			EncoderByMd5(System.currentTimeMillis()+"");
		}
	}
}
