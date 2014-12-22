package com.softlink.minitask.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.softlink.minitask.client.view.desktop.Header;
import com.softlink.minitask.client.view.desktop.ProjectListView;
import com.softlink.minitask.client.view.desktop.TaskListView;

public class MiniTask implements EntryPoint {

	public static final ClientFactory clientFactory = GWT
			.create(ClientFactory.class);
	public static final AppController appController = new AppController();

	@Override
	public void onModuleLoad() {
		RootPanel root = RootPanel.get("root");
		ProjectListView projectListView = new ProjectListView();
		root.add(projectListView, 10, 10);

		// root.add(clientFactory.getContainer());
		// appController.run();
	}

}
