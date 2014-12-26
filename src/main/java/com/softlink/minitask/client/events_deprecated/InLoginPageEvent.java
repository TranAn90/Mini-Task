package com.softlink.minitask.client.events_deprecated;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class InLoginPageEvent extends GwtEvent<InLoginPageEvent.Handler> {

	public interface Handler extends EventHandler {
		void inPlace(InLoginPageEvent event);
	}

	public static Type<InLoginPageEvent.Handler> TYPE = new Type<InLoginPageEvent.Handler>();

	@Override
	public Type<InLoginPageEvent.Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InLoginPageEvent.Handler handler) {
		handler.inPlace(this);
	}

}