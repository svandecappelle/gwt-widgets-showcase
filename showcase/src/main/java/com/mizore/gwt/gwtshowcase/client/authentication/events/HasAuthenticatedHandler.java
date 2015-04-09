package com.mizore.gwt.gwtshowcase.client.authentication.events;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasAuthenticatedHandler {

	HandlerRegistration addAuthenticatedHandler(AuthenticatedHandler event);

}
