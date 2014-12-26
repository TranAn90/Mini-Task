package com.softlink.minitask.client.events_deprecated;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InOrganizationDetailEvent extends GwtEvent<InOrganizationDetailEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InOrganizationDetailEvent event);
	}

	public static Type<InOrganizationDetailEvent.Handler> TYPE = new Type<InOrganizationDetailEvent.Handler>();

	@Override
	public Type<InOrganizationDetailEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InOrganizationDetailEvent.Handler handler) {
		handler.inPlace(this);
	}

}