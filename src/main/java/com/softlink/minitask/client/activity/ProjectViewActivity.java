package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.place.ProjectViewPlace;
import com.softlink.minitask.client.place.WelcomePlace;

public class ProjectViewActivity extends AbstractActivity {
	
	public ProjectViewActivity(ProjectViewPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
//		eventBus.fireEvent(new InLoginPageEvent());
	} 
	
	@Override
	public String mayStop() {
		return null;
	}

}
