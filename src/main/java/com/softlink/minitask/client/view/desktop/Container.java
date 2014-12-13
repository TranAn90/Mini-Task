package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.view.ContainerInf;

public class Container extends Composite implements ContainerInf {

	private static ContainerUiBinder uiBinder = GWT
			.create(ContainerUiBinder.class);
	
	@UiField HTMLPanel container;
	
	private Header header = new Header();

	interface ContainerUiBinder extends UiBinder<Widget, Container> {
	}

	public Container() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void inLoginPage() {
		container.clear();
		container.add(MiniTask.clientFactory.getLoginPage());
	}

	@Override
	public void inOrganizationPage() {
		container.clear();
		container.add(header);
		container.add(MiniTask.clientFactory.getOrganizationPage());
	}

	@Override
	public void inOrganizationDetail() {
		container.clear();
		container.add(header);
		container.add(MiniTask.clientFactory.getOrganizationDetail());
	}

}
