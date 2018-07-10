package org.seeker.entity;

import java.io.Serializable;
import java.util.List;

import org.seeker.common.base.entity.Page;

public class Menu extends Page implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1803808454796782067L;

	private String id;

    private String name;

    private String pid;

    private String url;

    private String type;

    private String status;

    private String createTime;

    private String createBy;

    private String remark;

    private int seq;

    private int mlevel;
    
    private String groupId;
    private String groupName;
    
    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	private List<Menu> sons;
    

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<Menu> getSons() {
		return sons;
	}

	public void setSons(List<Menu> sons) {
		this.sons = sons;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", pid=" + pid + ", url=" + url + ", type=" + type + ", status="
				+ status + ", createTime=" + createTime + ", createBy=" + createBy + ", remark=" + remark + ", seq="
				+ seq + ", mlevel=" + mlevel + ", groupId=" + groupId + ", groupName=" + groupName + ", sons=" + sons
				+ "]";
	}

}