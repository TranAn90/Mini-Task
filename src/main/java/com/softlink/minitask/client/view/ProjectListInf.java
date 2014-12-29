package com.softlink.minitask.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.softlink.minitask.shared.Task_Project;

public interface ProjectListInf extends IsWidget {
	
	void setProjectList(List<Task_Project> projectList);
	
	void activityStart();
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter {
		void desktopLoadData();
		
		void desktopRefresh();
		
		void goToProjectView(Task_Project project);
	}


}
