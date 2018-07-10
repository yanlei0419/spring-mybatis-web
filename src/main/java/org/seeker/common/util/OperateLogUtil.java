package org.seeker.common.util;

import javax.servlet.http.HttpServletRequest;

import org.seeker.entity.SysLog;
import org.seeker.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.seeker.common.base.spring3.SpringContextUtil;

public class OperateLogUtil {
	private static Logger l=LoggerFactory.getLogger(OperateLogUtil.class);
	
	
	public static void saveSysLog(HttpServletRequest req,String module,String type,String content,String remark){
//		User user=ToolUtils.getUserDeatil();
//		WebAuthenticationDetails wbd =ToolUtils.getWebAuthenticationDetail();
		SysLog po=new SysLog();
		po.setId(Uuid.getUUID());
		po.setOperateModule(module);
		po.setOperateType(type);
		po.setOperateTime(DateUtil.getTime());
		po.setOperateContent(content);
//		po.setOperate_by(user.getId());
//		po.setOperate_by(user.getUsername());
//		po.setOperate_ip(wbd.getRemoteAddress());
		//TODO 目前没有用户概念
		po.setOperateBy("admin");
		po.setOperateIp("0.0.0.0");
		po.setOperateRemark(remark);
		SysLogService biz=(SysLogService) SpringContextUtil.getBean("sysLogService");
		int result=biz.save(po);
		l.debug("操作日志保存成功 "+result+"条");
	}
	
	public static void saveSysLog(String module,String type,String content,String remark,String userName,String ip){
		SysLog po=new SysLog();
		po.setId(Uuid.getUUID());
		po.setOperateModule(module);
		po.setOperateType(type);
		po.setOperateTime(DateUtil.getTime());
		po.setOperateContent(content);
		po.setOperateBy(userName);
		po.setOperateIp(ip);
		po.setOperateRemark(remark);
		SysLogService biz=(SysLogService) SpringContextUtil.getBean("sysLogService");
		int result=biz.save(po);
		l.debug("操作日志保存成功 "+result+"条");
	}
}
