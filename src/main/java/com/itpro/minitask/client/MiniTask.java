package com.itpro.minitask.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.itpro.minitask.client.view.desktop.CreateTaskDialog;
import com.itpro.minitask.client.view.desktop.Header;

public class MiniTask implements EntryPoint {
	
	void addRootView() {
		RootPanel root = RootPanel.get("root");
		Header page = new Header();
		root.add(page);
		CreateTaskDialog createTask = new CreateTaskDialog( new CreateTaskDialog.Listener() {
		});
	}
	
	void addDevView() {
		RootPanel root = RootPanel.get("root");
		RenderWidget render = new RenderWidget();
		root.add(render);
	}

	@Override
	public void onModuleLoad() {
		addRootView();
//		addDevView();
	}

}
