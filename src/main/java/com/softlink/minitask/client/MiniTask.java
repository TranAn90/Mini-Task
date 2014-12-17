package com.softlink.minitask.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.softlink.minitask.client.view.desktop.CreateTaskDialog;
import com.softlink.minitask.client.view.desktop.Header;
import com.softlink.minitask.client.view.desktop.TaskListView;
import com.softlink.minitask.shared.Task_Data;
import com.softlink.minitask.shared.Task_Project;

public class MiniTask implements EntryPoint {

	void addRootView() {
		RootPanel root = RootPanel.get("root");
		Header page = new Header();
		root.add(page);
		TaskListView taskListView = new TaskListView();
		root.add(taskListView);
//		CreateTaskDialog create = new CreateTaskDialog(
//				new CreateTaskDialog.Listener() {
//				});
//		CreateProjectDialog taskDialog = new CreateProjectDialog(
//				new CreateProjectDialog.Listener() {
//				});
	}

	void addDevView() {
		RootPanel root = RootPanel.get("root");
		RenderWidget render = new RenderWidget();
		root.add(render);
	}

	@Override
	public void onModuleLoad() {
		addRootView();
		List<Task_Data> result = new ArrayList<Task_Data>();
		if (result.isEmpty())
			result.addAll(Task_Data.autoGenerateTasks());

		List<Task_Project> projects = new ArrayList<Task_Project>();
		if (projects.isEmpty())
			projects.addAll(Task_Project.autoGenerateProjects());
		// addDevView();
	}

}
