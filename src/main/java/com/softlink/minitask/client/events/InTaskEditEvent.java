package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InTaskEditEvent extends GwtEvent<InTaskEditEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InTaskEditEvent event);
	}

	public static Type<InTaskEditEvent.Handler> TYPE = new Type<InTaskEditEvent.Handler>();

	@Override
	public Type<InTaskEditEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InTaskEditEvent.Handler handler) {
		handler.inPlace(this);
	}

}