package com.seeker.common.base.entity;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;

public abstract class Page implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5638583498221345662L;
	/**
	 * 开始
	 */
	@JSONField(serialize=false)//不序列化该字段
	private Integer start;
	/**
	 * 开始
	 */
	private Integer begin;
	/**
	 * 结束
	 */
	private Integer end;
	/**
	 * 排序字段
	 */
	private String order;
	/**
	 * 升序还是降序
	 */
	private String sort;
	
	
	
	private Integer rows;
	private Integer page;
	
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;
	
	private String baseProjectPath;
	
	
	
	
	
	public String getBaseProjectPath() {
		return baseProjectPath;
	}
	public void setBaseProjectPath(String baseProjectPath) {
		this.baseProjectPath = baseProjectPath;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * ids 数组一般用于批量删除数据或者批量修改数据
	 */
	private String[] ids;
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Page() {
		super();
	}
	
}
