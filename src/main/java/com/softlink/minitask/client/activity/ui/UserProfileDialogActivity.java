package com.softlink.minitask.client.activity.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.softlink.minitask.client.AppController;
import com.softlink.minitask.client.view.desktop.ui.UserProfileDialog;

public class UserProfileDialogActivity implements UserProfileDialog.Presenter{

	@Override
	public void signOut() {
		String homeUrl = Window.Location.getHref();
		AppController.userRequest.getLogoutUrl(homeUrl, new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				Window.Location.assign(result);
			}	
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub			
			}
		});
	}

}
