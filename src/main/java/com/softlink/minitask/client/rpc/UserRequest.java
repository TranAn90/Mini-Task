package com.softlink.minitask.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.softlink.minitask.shared.UserProfiles;

@RemoteServiceRelativePath("user_request")
public interface UserRequest extends RemoteService{
	
	String getGoogleLoginUrl(String callbackURL);
	
	String getLogoutUrl(String callbackURL);
	
	UserProfiles getUserProfiles();
	
}
