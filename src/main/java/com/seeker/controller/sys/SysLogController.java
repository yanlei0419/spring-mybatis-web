package com.seeker.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seeker.common.base.controller.BaseController;
import com.seeker.common.util.JsonUtil;
import com.seeker.common.util.SysConstant;
import com.seeker.entity.SysLog;
import com.seeker.service.SysLogService;

@Controller
@RequestMapping("/syslog")
public class SysLogController extends BaseController {
	@Resource
	private SysLogService sysLogService;
	
	@RequestMapping("/getList")
	public void getList(HttpServletResponse res,SysLog po) throws IOException{
		this.initPage(po);
		PageInfo<SysLog> data=this.sysLogService.getPageList(po);
		String result=JsonUtil.toJSONStringByFastjson(data);
		PrintWriter pw=res.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}

//	{this.setOperateModuleName(SysConstant.SYS_LOG_NAME);}

	 
}
