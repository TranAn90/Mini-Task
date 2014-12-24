package com.softlink.minitask.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocalData implements Serializable {
	
	private boolean isLoad = false;
	private List<Task_Project> projectList = new ArrayList<Task_Project>();
	private List<Task_Data> taskList = new ArrayList<Task_Data>();
	
	public LocalData() {
		super();
	}

	public boolean isLoad() {
		return isLoad;
	}

	public void setLoad(boolean isLoad) {
		this.isLoad = isLoad;
	}

	public List<Task_Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Task_Project> projectList) {
		this.projectList = projectList;
	}

	public List<Task_Data> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task_Data> taskList) {
		this.taskList = taskList;
	}

}
