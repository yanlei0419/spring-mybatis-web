package com.seeker.service;

import java.util.List;

import com.seeker.entity.SysLog;

public interface SysLogService {
	public List<SysLog> getList(SysLog po);
	public int getCount(SysLog po);
	public int save(SysLog po);
}
