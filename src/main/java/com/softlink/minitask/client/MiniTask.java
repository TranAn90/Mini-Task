package com.softlink.minitask.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class MiniTask implements EntryPoint {
	
	public static final ClientFactory clientFactory = GWT.create(ClientFactory.class);
	public static final AppController appController = new AppController();
	
	@Override
	public void onModuleLoad() {
		RootPanel root = RootPanel.get("root");
		root.add(clientFactory.getContainer());
//		appController.run();
	}
	
}
