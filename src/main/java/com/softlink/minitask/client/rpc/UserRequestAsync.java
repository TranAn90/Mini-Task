package com.softlink.minitask.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minitask.shared.UserProfiles;

public interface UserRequestAsync {
	
	void getGoogleLoginUrl(String callbackURL, AsyncCallback<String> callback);
	
	void getLogoutUrl(String callbackURL, AsyncCallback<String> callback);
	
	void getUserProfiles(AsyncCallback<UserProfiles> callback);

}
