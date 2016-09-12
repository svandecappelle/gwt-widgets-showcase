package com.mizore.gwt.gwtshowcase.client.authentication.presenter;

import java.util.logging.Logger;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mizore.gwt.logging.client.control.presenter.LoggingPresenter;
import com.mizore.gwt.gwtshowcase.client.authentication.events.AuthenticatedEvent;
import com.mizore.gwt.gwtshowcase.client.authentication.events.AuthenticatedHandler;
import com.mizore.gwt.gwtshowcase.client.authentication.interfaces.AuthenticationDisplay;
import com.mizore.gwt.gwtshowcase.client.authentication.model.AuthenticationModel;
import com.mizore.gwt.gwtshowcase.shared.UserCredentials;

@Singleton
public class AuthenticationPresenter extends WidgetPresenter<AuthenticationDisplay> {

	private static final Logger logger = Logger.getLogger("Authentication");

	private UserCredentials userCredentials;

	@Inject
	private AuthenticationModel model;

	@Inject
	private LoggingPresenter loggingPresenter;

	@Inject
	public AuthenticationPresenter(AuthenticationDisplay display, EventBus eventBus) {
		super(display, eventBus);
		userCredentials = new UserCredentials();
	}

	@Override
	protected void onBind() {
		loggingPresenter.addLoggerType(logger.getName());

		registerHandler(display.getHasUserCredentialChanged().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				userCredentials.setUsername(event.getValue());
			}
		}));

		registerHandler(display.getHasPasswordCredentialChanged().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				userCredentials.setPassword(event.getValue());
			}
		}));

		registerHandler(display.getHasLoginRequestClickedHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				logger.info("Sending credentials");
				model.authenticate(userCredentials);
			}
		}));

		registerHandler(eventBus.addHandler(AuthenticatedEvent.getType(), new AuthenticatedHandler() {

			@Override
			public void onAuthenticated(AuthenticatedEvent event) {
				if (!event.isAuthenticated()) {
					logger.info("Not authenticated");
					revealDisplay();
				}else if(display.isVisible()){
					display.hide();
				}
			}
		}));
	}

	@Override
	protected void onUnbind() {
		// TODO Unbind here your events.

	}

	@Override
	protected void onRevealDisplay() {
		logger.info("Need authenticate");
		display.show();
	}

	public void authenticate() {
		logger.info("Checking credentials");
		model.checkAuthentication();
	}

}
