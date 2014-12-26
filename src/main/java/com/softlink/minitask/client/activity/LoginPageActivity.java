package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.WelcomePlace;

public class LoginPageActivity extends AbstractActivity {
	
	public LoginPageActivity(WelcomePlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
//		eventBus.fireEvent(new InLoginPageEvent());
		MiniTask.clientFactory.getContainer().inLoginPage();
		if(Storage.getUserProfiles().isLogin()) {
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
		}
		else {
			MiniTask.appController.userRequest.getGoogleLoginUrl(Window.Location.getHref(), new AsyncCallback<String>() {
				@Override
				public void onSuccess(String result) {
					MiniTask.clientFactory.getLoginPage().setLoginUrl(result);
				}
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			});
		}
	} 
	
	@Override
	public String mayStop() {
		return null;
	}

}
