package com.seeker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.seeker.entity.SysLog;
import com.seeker.mapper.SysLogMapper;
import com.seeker.service.SysLogService;
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService{
	private Logger l=LoggerFactory.getLogger(this.getClass());
	@Resource
	private SysLogMapper dao;

	@Override
	public List<SysLog> getList(SysLog po) {
		return this.dao.getList(po);
	}

	@Override
	public int getCount(SysLog po) {
		return this.dao.getCount(po);
	}

	@Override
	public int save(SysLog po) {
		
		try {
			String remark=po.getOperateRemark();
			String content=po.getOperateContent();
			if(null!=remark&&remark.length()>3900){
				l.debug("remark 长度超出3900 截取字符串 到 3900");
				remark=remark.substring(0, 3900);
				l.debug("原字符串>>>>>"+remark);
				po.setOperateRemark(remark);
			}
			if(null!=content&&content.length()>3900){
				l.debug("content 长度超出3900 截取字符串 到 3900");
				content=content.substring(0, 3900);
				l.debug("原字符串>>>>>"+content);
				po.setOperateContent(content);
			}
			return this.dao.save(po);
		} catch (Exception e) {
			String m="保存日志对象出现问题!!!!!!";
			l.error(m, e);
			return 0;
		}
	}

	public void setDao(SysLogMapper dao) {
		this.dao = dao;
	}

}
