package com.mizore.gwt.gwtshowcase.client.authentication.events;

import com.google.gwt.event.shared.EventHandler;

public interface ErrorAuthenticationHandler extends EventHandler {

	void onErrorAuthentication(ErrorAuthenticationEvent event);

}
