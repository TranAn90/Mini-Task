package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.view.desktop.ui.UserProfileDialog;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Header extends Composite {

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);
	
	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	@UiField Label lbNameApp;
	@UiField Label lbOrganizationName;
	@UiField Label lbUserName;
	@UiField Image imgNotify;
	@UiField Label lbLanguage;
	@UiField HorizontalPanel horOrganizationName;
	
	UserProfileDialog userDialog;

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}

	protected void SetTextForm() {
		lbNameApp.setText(CONSTANTS.AppNameMiniTask());
	}

	public Header() {
		initWidget(uiBinder.createAndBindUi(this));
		SetTextForm();
	}
	
	public void setInfo() {
		String userName = Storage.getUserProfiles().getName();
		userDialog = new UserProfileDialog(userName);
		lbUserName.setText(userName);
	}

	@UiHandler("lbOrganizationName")
	void onLbOrganizationNameClick(ClickEvent event) {
		Window.alert("Organization Name");
	}
	
	@UiHandler("lbUserName")
	void onLbUserNameClick(ClickEvent event) {
		if(!userDialog.isShowing) {
			userDialog.isShowing = true;
			int left = Window.getClientWidth() - 285;
			int top = lbUserName.getAbsoluteTop() + 40;
			userDialog.setPopupPosition(left, top);
			userDialog.show();
		}
	}
	
	@UiHandler("imgNotify")
	void onImgNotifyClick(ClickEvent event) {
		Window.alert("Notify");
	}
	
	@UiHandler("lbLanguage")
	void onLbLanguageClick(ClickEvent event) {
		Window.alert("Language");
	}
	
}
