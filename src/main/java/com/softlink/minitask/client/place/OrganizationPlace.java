package com.softlink.minitask.client.place;

import com.google.gwt.place.shared.Place;

public class OrganizationPlace extends Place{
	
	private String token;
	
	public OrganizationPlace(String token) {
		this.token = token;
	}

	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
}
