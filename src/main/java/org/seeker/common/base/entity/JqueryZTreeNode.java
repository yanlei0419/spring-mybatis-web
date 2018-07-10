package org.seeker.common.base.entity;

/**
 *jquery.ztree.core-3.5.js
 * 所需要的对象
 */
public class JqueryZTreeNode {
	private String id;
	private String pId;
	private String name;
	private Boolean checked;
	private Boolean open;
	private String iconOpen;
	private String iconClose;
	private String icon;
	private Boolean chkDisabled;
	
	public Boolean getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	private String iconSkin;//图片css
	
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getChecked() {
		return checked;
	}
	public Boolean getOpen() {
		return open;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean isOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public JqueryZTreeNode(String id, String pId, String name, Boolean checked, Boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
	}
	public JqueryZTreeNode() {
		super();
	}
	

}
