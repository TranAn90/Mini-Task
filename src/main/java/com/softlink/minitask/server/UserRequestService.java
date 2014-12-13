package com.softlink.minitask.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.softlink.minitask.client.rpc.UserRequest;
import com.softlink.minitask.shared.UserProfiles;

public class UserRequestService extends RemoteServiceServlet implements UserRequest{
	
	private static final UserService userService = UserServiceFactory
			.getUserService();

	@Override
	public String getGoogleLoginUrl(String callbackURL) {
		String loginUrl = userService.createLoginURL(callbackURL, null,
				"https://www.google.com/accounts/o8/id", null);
		return loginUrl;
	}

	@Override
	public String getLogoutUrl(String callbackURL) {
		return userService.createLogoutURL(callbackURL);
	}

	@Override
	public UserProfiles getUserProfiles() {
		if(userService.isUserLoggedIn()) {
			UserProfiles userProfiles = new UserProfiles();
			userProfiles.setName(userService.getCurrentUser().getNickname());
			userProfiles.setEmail(userService.getCurrentUser().getEmail());
			userProfiles.setLogin(true);
			return userProfiles;
		}
		return null;
	}

}
