package com.mizore.gwt.gwtshowcase.client.authentication.events;

import com.google.gwt.event.shared.GwtEvent;

public class ErrorAuthenticationEvent extends
		GwtEvent<ErrorAuthenticationHandler> {

	private static Type<ErrorAuthenticationHandler> type;

	public static Type<ErrorAuthenticationHandler> getType() {
		if (type == null) {
			type = new Type<ErrorAuthenticationHandler>();
		}
		return type;
	}

	public ErrorAuthenticationEvent() {
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ErrorAuthenticationHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(ErrorAuthenticationHandler handler) {
		handler.onErrorAuthentication(this);
	}

}
