package org.seeker.entity;

import org.seeker.common.base.entity.Page;

public class GroupMenu extends Page {
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 8264847233129185634L;

	private String id;

    private String menuid;

    private String groupid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}