package org.seeker.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjIoUtils {
	private static Logger l=LoggerFactory.getLogger(ObjIoUtils.class);
	
	private final static String path=SysConstant.OBJECT_STREAM_PATH;
	
	 public static String errorMSG(String msg,Object obj)  {
		 String base=System.getProperty("webapp.root");
		 String webPath=base+path+Uuid.getUUID();
		 try {
			File file = new File(webPath);
			 l.error(msg+" ==> 生成的文件名"	+file.getPath());
			 createObj(file, obj);
			 return webPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return webPath;
	 }
	 
	 public static String errorMSG(Object obj)  {
		 String base=System.getProperty("webapp.root");
		 String webPath=base+path+Uuid.getUUID();
		 try {
			File file = new File(webPath);
			 l.error(" ==> 生成的文件名"	+file.getPath());
			 createObj(file, obj);
			 return webPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return webPath;
	 }

	//（一）先写入对象
    public static void createObj(File file,Object obj)  {
        try {
			//2.创建流通道
			FileOutputStream fos = new FileOutputStream(file);
			//3.创建对象输出流
			ObjectOutputStream objOP = new ObjectOutputStream(fos);
			//5.向目标路径文件写入对象
			objOP.writeObject(obj);
			//6.关闭资源
			objOP.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) {
	}
    //再读取对象
    public static Object readObj(String fileName)  {
        try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream objIP = new ObjectInputStream(fis);
			//读取对象数据，需要将对象流强制转换为 要写入对象的类型
			Object obj = objIP.readObject();
			System.out.println("输出流 : "+obj);
			objIP.close();  
			return obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }

}
