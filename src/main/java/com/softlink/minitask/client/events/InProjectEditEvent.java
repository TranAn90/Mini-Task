package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InProjectEditEvent extends GwtEvent<InProjectEditEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InProjectEditEvent event);
	}

	public static Type<InProjectEditEvent.Handler> TYPE = new Type<InProjectEditEvent.Handler>();

	@Override
	public Type<InProjectEditEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InProjectEditEvent.Handler handler) {
		handler.inPlace(this);
	}

}