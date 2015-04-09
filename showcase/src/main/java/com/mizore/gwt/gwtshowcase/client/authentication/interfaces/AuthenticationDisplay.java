package com.mizore.gwt.gwtshowcase.client.authentication.interfaces;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public interface AuthenticationDisplay extends WidgetDisplay {

	HasValueChangeHandlers<String> getHasUserCredentialChanged();

	HasValueChangeHandlers<String> getHasPasswordCredentialChanged();

	void show();

	HasClickHandlers getHasLoginRequestClickedHandlers();

	void hide();

	boolean isVisible();

}
