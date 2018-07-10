package org.seeker.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFileUtil {
	public static boolean flag = false;
	
	public static void writeFile(String filePath, String fileName, String content) throws IOException {

		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		File boFile = new File(filePath + fileName);
		if (!boFile.exists())
			boFile.createNewFile();
		FileWriter fwrtier = new FileWriter(boFile, flag);
		PrintWriter pw = new PrintWriter(fwrtier);
		pw.write(content + "\t\n");
		pw.flush();
		pw.close();
		
		if(!flag){
			flag=true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		writeFile("d://da/sa/le/", "1", "ceshi");
	}

//	public static void writeFile(LineDataPo po) throws IOException {
//		String filePath = po.getFilePath();
//		String fileName = po.getFileName();
//
//		File dir = new File(filePath);
//		if (!dir.exists())
//			dir.mkdirs();
//		File boFile = new File(filePath + fileName);
//
//		if (!boFile.exists())
//			boFile.createNewFile();
//		FileWriter fwrtier = new FileWriter(boFile, true);
//		PrintWriter pw = new PrintWriter(fwrtier);
//		for (int i = 0; i < po.getNullLine(); i++) {
//			pw.println();
//		}
//		pw.write(po.getLineData());
//		pw.println();
//		for (int i = 0; i < po.getNullLine(); i++) {
//			pw.println();
//		}
//		pw.flush();
//		pw.close();
//	}

	public static void writeFile(String filePath, String fileName, String content, int nullLine) throws IOException {

		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		File boFile = new File(filePath + fileName);

		if (!boFile.exists())
			boFile.createNewFile();
		FileWriter fwrtier = new FileWriter(boFile, flag);
		PrintWriter pw = new PrintWriter(fwrtier);
		for (int i = 0; i < nullLine; i++) {
			pw.println();
		}
		pw.write(content);
		pw.println();
		for (int i = 0; i < nullLine; i++) {
			pw.println();
		}
		pw.flush();
		pw.close();
		if(!flag){
			flag=true;
		}
	}

}
