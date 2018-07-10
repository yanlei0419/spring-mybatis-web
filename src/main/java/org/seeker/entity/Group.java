package org.seeker.entity;

import java.util.ArrayList;
import java.util.List;

import org.seeker.common.base.entity.Page;

public class Group extends Page {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6960454898750194003L;

	private String id;

    private String groupName;

    private String remark;

    private String status;
    
    private String userId;
    
    private String flag;
    private List<Menu> ms=new ArrayList<Menu>();
    

    public List<Menu> getMs() {
		return ms;
	}

	public void setMs(List<Menu> ms) {
		this.ms = ms;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}