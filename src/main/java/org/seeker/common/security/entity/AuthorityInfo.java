package org.seeker.common.security.entity;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class AuthorityInfo implements Serializable,GrantedAuthority {
	
	
	
	
	
	
	
    private String authority;

    public AuthorityInfo() {  }
    public AuthorityInfo(String authority) {
        this.setAuthority(authority);
    }
    
    @Override
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
