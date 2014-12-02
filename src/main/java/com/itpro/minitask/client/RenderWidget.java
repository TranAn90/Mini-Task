package com.itpro.minitask.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class RenderWidget extends AbsolutePanel {

	public RenderWidget() {
		this.setSize("100px", "100px");
		Label lb = new Label("This is label");
		this.add(lb);
	}
	
}
