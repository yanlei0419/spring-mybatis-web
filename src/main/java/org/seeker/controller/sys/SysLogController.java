package org.seeker.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import org.seeker.common.util.JsonUtil;
import org.seeker.entity.SysLog;
import org.seeker.service.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.seeker.common.base.controller.BaseController;

@Controller
@RequestMapping("/syslog")
public class SysLogController extends BaseController {
	@Resource
	private SysLogService sysLogService;
	
	@RequestMapping("/getList")
	public void getList(HttpServletResponse res,SysLog po) throws IOException{
		this.initPage(po);
		PageInfo<SysLog> data=this.sysLogService.getPageList(po);
		String result= JsonUtil.toJSONStringByFastjson(data);
		PrintWriter pw=res.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}

//	{this.setOperateModuleName(SysConstant.SYS_LOG_NAME);}

	 
}
