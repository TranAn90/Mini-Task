package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InOrganizationPageEvent extends GwtEvent<InOrganizationPageEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InOrganizationPageEvent event);
	}

	public static Type<InOrganizationPageEvent.Handler> TYPE = new Type<InOrganizationPageEvent.Handler>();

	@Override
	public Type<InOrganizationPageEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InOrganizationPageEvent.Handler handler) {
		handler.inPlace(this);
	}

}