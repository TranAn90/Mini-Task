package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.place.TaskListPlace;

public class SlideBar extends Composite {

	private static SlideBarUiBinder uiBinder = GWT
			.create(SlideBarUiBinder.class);
	
	@UiField Label projectTab;
	@UiField Label taskTab;
	@UiField Label eventTab;

	interface SlideBarUiBinder extends UiBinder<Widget, SlideBar> {
	}

	public SlideBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("projectTab")
	void onProjectTabClick(ClickEvent event) {
		MiniTask.clientFactory.getPlaceController().goTo(new ProjectListPlace());
	}
	
	@UiHandler("taskTab")
	void onTaskTabClick(ClickEvent event) {
		MiniTask.clientFactory.getPlaceController().goTo(new TaskListPlace());
	}
	
	@UiHandler("eventTab")
	void onEventTabClick(ClickEvent event) {
		Window.alert("Go to event!");
	}
	
	public void setProjectTab() {
		projectTab.setStyleName("SlideBar-Obj2 button-greenStyle");
		taskTab.setStyleName("SlideBar-Obj1 button-greenStyle");
	}
	
	public void setTaskTab() {
		projectTab.setStyleName("SlideBar-Obj1 button-greenStyle");
		taskTab.setStyleName("SlideBar-Obj2 button-greenStyle");
	}
	
}
