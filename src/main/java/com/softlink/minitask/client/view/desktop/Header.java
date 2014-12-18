package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.softlink.minitask.client.AppConstants;

public class Header extends Composite {

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);
	@UiField
	Label lbNameApp;

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}

	public Header() {
		initWidget(uiBinder.createAndBindUi(this));
		SetTextForm();
	}

	private final AppConstants CONSTANTS = GWT.create(AppConstants.class);

	protected void SetTextForm() {
		lbNameApp.setText(CONSTANTS.AppNameMiniTask());
	}

}
