package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.view.OrganizationDetailInf;

public class OrganizationDetail extends Composite implements OrganizationDetailInf{

	private static CompanyDetailUiBinder uiBinder = GWT
			.create(CompanyDetailUiBinder.class);

	interface CompanyDetailUiBinder extends UiBinder<Widget, OrganizationDetail> {
	}

	public OrganizationDetail() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
