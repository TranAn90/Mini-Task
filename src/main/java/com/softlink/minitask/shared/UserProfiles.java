package com.softlink.minitask.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Organization;

public class UserProfiles implements Serializable{
	
	private String name;
	private String email;
	private List<System_Organization> organizationList = new ArrayList<System_Organization>();
	private List<Invite_Token> inviteTokenList = new ArrayList<Invite_Token>();
	private boolean isLogin = false;
	
	public UserProfiles() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<System_Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<System_Organization> organizationList) {
		this.organizationList = organizationList;
	}
	
	public System_Organization findOrganization(String organizationId) {
		for(System_Organization organization: organizationList)
			if(organization.getId().equals(organizationId))
				return organization;
		return null;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public List<Invite_Token> getInviteTokenList() {
		return inviteTokenList;
	}

	public void setInviteTokenList(List<Invite_Token> inviteTokenList) {
		this.inviteTokenList = inviteTokenList;
	}

}
