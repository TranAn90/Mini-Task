package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InTaskViewEvent extends GwtEvent<InTaskViewEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InTaskViewEvent event);
	}

	public static Type<InTaskViewEvent.Handler> TYPE = new Type<InTaskViewEvent.Handler>();

	@Override
	public Type<InTaskViewEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InTaskViewEvent.Handler handler) {
		handler.inPlace(this);
	}

}