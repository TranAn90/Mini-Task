package com.softlink.minitask.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface ContainerInf extends IsWidget{
	
	//App events
	void inLoginPage();
	
	void inOrganizationPage();
	
	void inOrganizationDetail();
	
	void inTaskList();
	
	void inProjectList();
	
	void inProjectView();
	
	void onStartUp();
	
	//Desktop events
	void scrollToBottom();
	
	void updateHeaderInfo();
	
}
