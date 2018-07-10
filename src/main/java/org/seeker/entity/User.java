package org.seeker.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.seeker.common.base.entity.Page;
import org.seeker.common.security.entity.AuthorityInfo;
import org.springframework.security.core.userdetails.UserDetails;

public class User extends Page implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4654384577145517936L;

	private String id;

    private String username;

    private String password;

    private String name;

    private String tel;

    private String email;

    private String createTime;

    private String createBy;

    private String status;

    private String remark;
    
    private String flag;
    
    private String groupId;
    
    private List<Group> groups;
    
    private  List<Menu> menus;
    
    private String oldPassword;
    private String newPassword;
    
    private String sessionId;
    
    


	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
    @Override  
    public boolean equals(Object obj) {   
        if (obj instanceof User && this.hashCode() == obj.hashCode()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    @Override  
    public int hashCode() {  
    	return this.username.hashCode() + this.password.hashCode();  
    }
    
    private boolean isAccountNonExpired=true;//帐户是否过期
    private boolean isAccountNonLocked=true;//登录锁  禁用启用状态 帐户是否已锁定
    private boolean isCredentialsNonExpired=true; //凭证是否过期
    private boolean isEnabled=true;//是否被启用
    
    private Set<AuthorityInfo> authorities=new HashSet<AuthorityInfo>();
    
    
//    {
//    	AuthorityInfo authorityInfo=new AuthorityInfo("ROLE_USER");
//		authorities.add(authorityInfo);
//    }
    private AuthorityInfo info;
	public void addAuthoritie(String val) {
		info=new AuthorityInfo(val);
		authorities.add(info);
	}

	@Override
	public Set<AuthorityInfo> getAuthorities() {
		return authorities;
	}
//	@Override
//	public Collection<VeAuthority> getAuthorities() {
//		return authorities;
//	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}  
	
	

//    public void setAuthorities(Collection<Authority> authorities) {
    public void setAuthorities(Set<AuthorityInfo> authorities) {
		this.authorities = authorities;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}