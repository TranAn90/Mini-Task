package com.softlink.minitask.client.place;

import com.google.gwt.place.shared.Place;

public class TaskViewPlace extends Place {

	private String token;
	
	public TaskViewPlace(String token) {
		this.token = token;
	}

	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
}
