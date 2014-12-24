package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minilib.shared.System_Organization;
import com.softlink.minitask.client.AppConstants;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.TaskListPlace;
import com.softlink.minitask.client.view.desktop.ui.UserProfileDialog;

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
		lbOrganizationName.setVisible(false);
	}
	
	public void setInfo() {
		String userName = Storage.getUserProfiles().getName();
		System_Organization organization = Storage.getUserProfiles().
				findOrganization(Storage.getUserProfiles().getOrganizationCurrently());
		userDialog = new UserProfileDialog(userName);
		lbUserName.setText(userName);
		if(organization != null) {
			lbOrganizationName.setVisible(true);
			lbOrganizationName.setText(organization.getName());
		}
		else {
			lbOrganizationName.setVisible(false);
			lbOrganizationName.setText("");
		}
	}

	@UiHandler("lbOrganizationName")
	void onLbOrganizationNameClick(ClickEvent event) {
		Place currentPlace = MiniTask.clientFactory.getPlaceController().getWhere();
		if(currentPlace instanceof OrganizationPlace)
			MiniTask.clientFactory.getPlaceController().goTo(new TaskListPlace());
		else
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
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
