package org.seeker.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.seeker.entity.SysLog;

public interface SysLogService {
	List<SysLog> getList(SysLog po);
	int getCount(SysLog po);
	int save(SysLog po);
	PageInfo<SysLog> getPageList(SysLog po);
}
