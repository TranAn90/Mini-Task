package com.softlink.minitask.client.events_deprecated;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class OnStartUpEvent extends GwtEvent<OnStartUpEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(OnStartUpEvent event);
	}

	public static Type<OnStartUpEvent.Handler> TYPE = new Type<OnStartUpEvent.Handler>();

	@Override
	public Type<OnStartUpEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OnStartUpEvent.Handler handler) {
		handler.inPlace(this);
	}

}