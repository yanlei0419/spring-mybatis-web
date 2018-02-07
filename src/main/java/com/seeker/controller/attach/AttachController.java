package com.seeker.controller.attach;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.seeker.common.util.SysConstant;
import com.seeker.common.util.VeUtil;
@Controller
@RequestMapping(value="/attach")
public class AttachController {
	private Logger l=LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/pdmFilesUpload",method=RequestMethod.POST)
	public void pdmFilesUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		l.debug("uploading.......");
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				File nfile = getDSFilePath(request, file.getOriginalFilename());
				// 转存文件
				file.transferTo(nfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().print("SUCCESS");
		resp.getWriter().flush();
		resp.getWriter().close();
		l.debug("end.......");
	}
	
	
	@RequestMapping(value="/DicFilesUpload",method=RequestMethod.POST)
	public void dicFilesUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		l.debug("UPLOADING.......");
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				File nfile=getDicFilePath(request, file.getOriginalFilename());
				// 转存文件
				file.transferTo(nfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().print("SUCCESS");
		resp.getWriter().flush();
		resp.getWriter().close();
		l.debug("END.......");
	}
	
	
	/**
	 * @param request
	 * @param fileName
	 * @return
	 */
	private File getDicFilePath(HttpServletRequest request,String fileName) {
		// 文件保存路径
		String ve=request.getParameter("ve");
		String filePath="";
		ifCreateFolder(filePath);
		String pdmFilesName =filePath + fileName;
		l.debug(pdmFilesName);
		File file=new File(pdmFilesName);
		return file;
	}
	
	

	/**
	 */
	private File getDSFilePath(HttpServletRequest request,String fileName) {
		// 文件保存路径
		String filePath = SysConstant.uploadPath ;
		ifCreateFolder(filePath);
		String pdmFilesName =filePath + fileName;
		l.debug(pdmFilesName);
		File file=new File(pdmFilesName);
		return file;
	}
	
	private void ifCreateFolder(String filePath) {
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public static void recurDelete(File f) {
		try {
			for (File fi : f.listFiles()) {
				if (fi.isDirectory()) {
					recurDelete(fi);
				} else {
					fi.delete();
				}
			}
			f.delete();
		} catch (NullPointerException n) {
			System.out.println("Sorry,No such file");
		}
	}

	public static void main(String[] args) {
		AttachController ac=new AttachController();
		ac.ifCreateFolder("d://abc//bcd//");
	}

}
