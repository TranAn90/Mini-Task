package com.softlink.minitask.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.softlink.minitask.client.events.InOrganizationDetailEvent;
import com.softlink.minitask.client.place.OrganizationPlace;

public class OrganizationDetailActivity extends AbstractActivity {
	
	String token;
	
	public OrganizationDetailActivity(OrganizationPlace place) {
		this.token = place.getToken();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		eventBus.fireEvent(new InOrganizationDetailEvent());
	} 
	
	@Override
	public String mayStop() {
		return null;
	}

}
