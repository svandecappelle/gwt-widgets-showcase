package com.mizore.gwt.gwtshowcase.client.authentication.events;

import com.google.gwt.event.shared.GwtEvent;

public class AuthenticatedEvent extends GwtEvent<AuthenticatedHandler> {

	private static Type<AuthenticatedHandler> type;

	public static Type<AuthenticatedHandler> getType() {
		if (type == null) {
			type = new Type<AuthenticatedHandler>();
		}
		return type;
	}

	private Boolean authenticated;

	public AuthenticatedEvent(Boolean isAuthenticated) {
		this.authenticated = isAuthenticated;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AuthenticatedHandler> getAssociatedType() {
		return getType();
	}

	@Override
	protected void dispatch(AuthenticatedHandler handler) {
		handler.onAuthenticated(this);
	}

	public Boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

}
