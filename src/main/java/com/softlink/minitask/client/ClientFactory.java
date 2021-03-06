package com.softlink.minitask.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.softlink.minitask.client.view.ContainerInf;
import com.softlink.minitask.client.view.LoginPageInf;
import com.softlink.minitask.client.view.OrganizationDetailInf;
import com.softlink.minitask.client.view.OrganizationPageInf;
import com.softlink.minitask.client.view.ProjectListInf;
import com.softlink.minitask.client.view.ProjectViewInf;
import com.softlink.minitask.client.view.TaskListInf;

public interface ClientFactory {
	
	EventBus getEventBus();

	PlaceController getPlaceController();
	
	ContainerInf getContainer();
	
	LoginPageInf getLoginPage();
	
	OrganizationPageInf getOrganizationPage();
	
	OrganizationDetailInf getOrganizationDetail();
	
	TaskListInf getTaskList();
	
	ProjectListInf getProjectList();
	
	ProjectViewInf getProjectView();
	
}
