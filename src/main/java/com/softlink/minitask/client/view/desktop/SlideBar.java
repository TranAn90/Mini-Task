package com.softlink.minitask.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SlideBar extends Composite {

	private static SlideBarUiBinder uiBinder = GWT
			.create(SlideBarUiBinder.class);

	interface SlideBarUiBinder extends UiBinder<Widget, SlideBar> {
	}

	public SlideBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
