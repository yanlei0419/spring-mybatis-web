package org.seeker.common.util;
  
import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
  
public class EncodeFile {  
  
    /** 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
    }  
	public static void checkFile(File file) throws Exception {
		if (file.isFile()) {
			// 读取某个文件夹下的所有文件
//			System.out.println("文件：" + file.getName());
			codeString(file);
		}
		if (file.isDirectory()) {
			// 读取某个文件夹下的所有文件夹
//			System.out.println("文件夹：" + file.getName());
			
			File[] tempList = file.listFiles();
//			System.out.println("该目录下对象个数：" + tempList.length);
			for (File f : tempList) {
				checkFile(f);
			}
		}

	}

    /** 
     * 判断文件的编码格式 
     * @param fileName :file 
     * @return 文件编码格式 
     * @throws Exception 
     */  
    public static String codeString(File file) throws Exception{  
          
        if(file==null || !file.exists()){  
            System.out.println("文件不存在..."+file.getAbsolutePath());  
            return null;  
        }  
          
        @SuppressWarnings("resource")
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));  
        int p = (bin.read() << 8) + bin.read();  
        String code = null;  
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数  
        switch (p) {  
            case 0xefbb:  
                code = "UTF-8";  
                break;  
            case 0xfffe:  
                code = "Unicode";  
                System.out.println(file.getName());
                break;  
            case 0xfeff:  
                code = "UTF-16BE";  
                System.out.println(file.getName());
                break;  
            case 0x5c75:  
                code = "ANSI|ASCII" ;  
                System.out.println(file.getName());
                break ;  
            default:  
                code = "GBK";  
                System.out.println(file.getName());
        }  
           
        return code;  
    }  
}  