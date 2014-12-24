package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.view.ContainerInf;

public class Container extends Composite implements ContainerInf {

	private static ContainerUiBinder uiBinder = GWT
			.create(ContainerUiBinder.class);
	
	public static final int headerHeight = 40;
	
	@UiField DockLayoutPanel container;
	
	Header header = new Header();
	SlideBar slideBar = new SlideBar();
	ScrollPanel scrollAbleContent = new ScrollPanel();

	interface ContainerUiBinder extends UiBinder<Widget, Container> {
	}

	public Container() {
		initWidget(uiBinder.createAndBindUi(this));
		container.setHeight(Window.getClientHeight() + "px");
	}
	
	void clearContent() {
		container.clear();
		scrollAbleContent.clear();
	}

	@Override
	public void inLoginPage() {
		clearContent();
		container.add(scrollAbleContent);
		scrollAbleContent.add(MiniTask.clientFactory.getLoginPage());
	}

	@Override
	public void inOrganizationPage() {
		clearContent();
		container.addNorth(header, headerHeight);
		container.add(scrollAbleContent);
		scrollAbleContent.add(MiniTask.clientFactory.getOrganizationPage());
	}

	@Override
	public void inOrganizationDetail() {
		clearContent();
		container.addNorth(header, headerHeight);
		container.add(scrollAbleContent);
		scrollAbleContent.add(MiniTask.clientFactory.getOrganizationDetail());
	}

	@Override
	public void onStartUp() {
		header.setInfo();
	}

	@Override
	public void inTaskList() {
		clearContent();
		container.addNorth(header, headerHeight);
		container.addWest(slideBar, 80);
		container.add(MiniTask.clientFactory.getTaskList());
	}

	@Override
	public void scrollToBottom() {
		scrollAbleContent.scrollToBottom();
	}

	@Override
	public void updateHeaderInfo() {
		header.setInfo();
	}

}
