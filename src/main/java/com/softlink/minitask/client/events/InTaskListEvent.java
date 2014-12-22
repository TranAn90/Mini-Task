package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InTaskListEvent extends GwtEvent<InTaskListEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InTaskListEvent event);
	}

	public static Type<InTaskListEvent.Handler> TYPE = new Type<InTaskListEvent.Handler>();

	@Override
	public Type<InTaskListEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InTaskListEvent.Handler handler) {
		handler.inPlace(this);
	}

}