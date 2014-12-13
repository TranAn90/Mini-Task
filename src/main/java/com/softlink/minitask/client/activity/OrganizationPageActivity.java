package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.events.InOrganizationPageEvent;
import com.softlink.minitask.client.place.OrganizationPlace;

public class OrganizationPageActivity extends AbstractActivity {
	
	public OrganizationPageActivity(OrganizationPlace place) {
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		eventBus.fireEvent(new InOrganizationPageEvent());
	} 
	
	@Override
	public String mayStop() {
		return null;
	}

}
