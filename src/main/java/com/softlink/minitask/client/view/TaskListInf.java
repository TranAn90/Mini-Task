package com.softlink.minitask.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.softlink.minitask.shared.Task_Data;

public interface TaskListInf extends IsWidget {
	
	void setTaskList(List<Task_Data> taskList);
	
	void activityStart();
	
	void setPresenter(Presenter presenter);
	
	public interface Presenter {
		void desktopLoadData();
	}

}
