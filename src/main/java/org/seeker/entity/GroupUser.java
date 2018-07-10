package org.seeker.entity;

import org.seeker.common.base.entity.Page;

public class GroupUser extends Page {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6209903035612921329L;

	private String id;

    private String userid;

    private String groupid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}