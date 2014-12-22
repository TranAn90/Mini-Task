package com.softlink.minitask.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InProjectListEvent extends GwtEvent<InProjectListEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InProjectListEvent event);
	}

	public static Type<InProjectListEvent.Handler> TYPE = new Type<InProjectListEvent.Handler>();

	@Override
	public Type<InProjectListEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InProjectListEvent.Handler handler) {
		handler.inPlace(this);
	}

}