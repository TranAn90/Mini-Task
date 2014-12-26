package com.softlink.minitask.client.events_deprecated;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InProjectViewEvent extends GwtEvent<InProjectViewEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InProjectViewEvent event);
	}

	public static Type<InProjectViewEvent.Handler> TYPE = new Type<InProjectViewEvent.Handler>();

	@Override
	public Type<InProjectViewEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InProjectViewEvent.Handler handler) {
		handler.inPlace(this);
	}

}