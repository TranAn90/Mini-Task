package com.softlink.minitask.client.place;

import com.google.gwt.place.shared.Place;

public class ProjectViewPlace extends Place {

	private String token;
	
	public ProjectViewPlace(String token) {
		this.token = token;
	}

	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
}
