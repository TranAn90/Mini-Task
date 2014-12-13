package com.softlink.minitask.shared;

import java.io.Serializable;
import java.util.List;

public class UserProfiles implements Serializable{
	
	private String name;
	private String email;
	private List<String> organizationList;
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

	public List<String> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<String> organizationList) {
		this.organizationList = organizationList;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
