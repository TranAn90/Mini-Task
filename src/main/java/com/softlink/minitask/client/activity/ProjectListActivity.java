package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.AppController.Storage;
import com.softlink.minitask.client.MiniTask;
import com.softlink.minitask.client.place.OrganizationPlace;
import com.softlink.minitask.client.place.ProjectListPlace;
import com.softlink.minitask.client.view.ProjectListInf;

public class ProjectListActivity extends AbstractActivity {
	
	ProjectListInf projectList;
	
	public ProjectListActivity(ProjectListPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		if(Storage.getUserProfiles().getOrganizationCurrently() == null) {
			MiniTask.clientFactory.getPlaceController().goTo(new OrganizationPlace(null));
		} 
		else {
			MiniTask.clientFactory.getContainer().inProjectList();
		}
	} 
	
	@Override
	public String mayStop() {
		return null;
	}

}
