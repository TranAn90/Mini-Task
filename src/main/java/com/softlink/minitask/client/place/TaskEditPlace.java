package com.softlink.minitask.client.place;

import com.google.gwt.place.shared.Place;

public class TaskEditPlace extends Place {

	private String token;
	
	public TaskEditPlace(String token) {
		this.token = token;
	}

	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
}
