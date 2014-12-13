package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.view.OrganizationPageInf;

public class OrganizationPage extends Composite implements OrganizationPageInf{

	private static OrganizationPageUiBinder uiBinder = GWT
			.create(OrganizationPageUiBinder.class);

	interface OrganizationPageUiBinder extends
			UiBinder<Widget, OrganizationPage> {
	}

	public OrganizationPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
