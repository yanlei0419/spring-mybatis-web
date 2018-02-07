package com.seeker.mapper;

import java.util.List;

import com.seeker.entity.SysLog;

public interface SysLogMapper  {
	public List<SysLog> getList(SysLog po);
	public int getCount(SysLog po);
	public int save(SysLog po);
}