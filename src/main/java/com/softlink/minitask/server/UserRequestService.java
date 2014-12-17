package com.softlink.minitask.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.softlink.minilib.shared.Invite_Token;
import com.softlink.minilib.shared.System_Account;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.rpc.UserRequest;
import com.softlink.minitask.shared.UserProfiles;

public class UserRequestService extends RemoteServiceServlet implements UserRequest{
	
	private static final UserService userService = UserServiceFactory.getUserService();

	@Override
	public String getGoogleLoginUrl(String callbackURL) {
//		String loginUrl = userService.createLoginURL(callbackURL, null,
//				"https://www.google.com/accounts/o8/id", null);
		String loginUrl = userService.createLoginURL(callbackURL);
		return loginUrl;
	}

	@Override
	public String getLogoutUrl(String callbackURL) {
		return userService.createLogoutURL(callbackURL);
	}

	@Override
	public UserProfiles getUserProfiles() {
		if(userService.isUserLoggedIn()) {
			//check for new account
			String user_email = userService.getCurrentUser().getEmail();
			String user_name = userService.getCurrentUser().getNickname();
			System_Account current_user = ofy().load().type(System_Account.class)
					.id(user_email).now();
			if(current_user == null) {
				current_user = new System_Account();
				current_user.setEmail(user_email);
				ofy().save().entity(current_user).now();
			}
			//create user profiles
			UserProfiles userProfiles = new UserProfiles();
			userProfiles.setName(user_name);
			userProfiles.setEmail(user_email);
			userProfiles.setLogin(true);
			List<System_Organization> organizationList = new ArrayList<System_Organization>();
			if(current_user.getOrganizationList() != null)
				for(String organizationId: current_user.getOrganizationList()) {
					System_Organization organization = ofy().load().type(System_Organization.class).id(organizationId).now();
					if(organization != null)
						organizationList.add(organization);
				}
			userProfiles.setOrganizationList(organizationList);
			List<Invite_Token> inviteTokenList = new ArrayList<Invite_Token>();
			inviteTokenList.addAll(ofy().load().type(Invite_Token.class).filter("account" , user_email).list());
			userProfiles.setInviteTokenList(inviteTokenList);
			return userProfiles;
		}
		return null;
	}

}
