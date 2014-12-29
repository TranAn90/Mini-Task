package com.softlink.minitask.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.softlink.minitask.shared.Task_Project;

public interface ProjectViewInf extends IsWidget {
	
	void setProject(Task_Project project);
	
	void activityStart();
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter {
		void desktopLoadData();
	}
	
}
