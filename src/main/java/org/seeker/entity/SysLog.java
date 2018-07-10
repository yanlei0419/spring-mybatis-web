package org.seeker.entity;

import org.seeker.common.base.entity.Page;

public class SysLog extends Page {
	
	private static final long serialVersionUID = -3798445635985301288L;
	private String TABLE_NAME="sys_log";
	
    private String id;

    private String operateModule;

    private String operateContent;

    private String operateType;

    private String operateBy;

    private String operateTime;

    private String operateIp;

    private String operateRemark;
    
    private String startTime;

    private String endTime;

    public String getTABLE_NAME() {
		return TABLE_NAME;
	}

	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateModule() {
        return operateModule;
    }

    public void setOperateModule(String operateModule) {
        this.operateModule = operateModule;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {

        this.operateRemark = operateRemark;
    }



}