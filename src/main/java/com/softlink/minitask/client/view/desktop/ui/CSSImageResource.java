package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface CSSImageResource extends ClientBundle {
	@Source("/images/star_red.png")
	ImageResource imgPriority_Hight();

	@Source("/images/star_orange.png")
	ImageResource imgPriority_Medium();

	@Source("/images/star_white.png")
	ImageResource imgPriority_Low();

}
