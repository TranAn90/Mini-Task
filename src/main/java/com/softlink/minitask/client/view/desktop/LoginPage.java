package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.view.LoginPageInf;

public class LoginPage extends Composite implements LoginPageInf{

	private static LoginPageUiBinder uiBinder = GWT
			.create(LoginPageUiBinder.class);
	
	@UiField Anchor btnLogin;
	String loginUrl;

	interface LoginPageUiBinder extends UiBinder<Widget, LoginPage> {
	}

	public LoginPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setLoginUrl(String url) {
		loginUrl = url;
	}

	@UiHandler("btnLogin")
	void onBtnLoginClick(ClickEvent event) {
		Window.Location.assign(loginUrl);
	}
}
