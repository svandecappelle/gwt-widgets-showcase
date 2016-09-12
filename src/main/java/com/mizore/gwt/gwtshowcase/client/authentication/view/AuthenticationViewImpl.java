package com.mizore.gwt.gwtshowcase.client.authentication.view;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.mizore.gwt.apprise.client.Popup;
import com.mizore.gwt.gwtshowcase.client.authentication.interfaces.AuthenticationDisplay;

public class AuthenticationViewImpl extends Popup implements AuthenticationDisplay {

	private TextBox username;
	private PasswordTextBox password;
	private Button loginButton;

	public AuthenticationViewImpl() {
		this.username = new TextBox();
		this.password = new PasswordTextBox();
		this.loginButton = this.addButton("Login");

		InlineLabel userLabel = new InlineLabel("Username");
		InlineLabel passwordLabel = new InlineLabel("Password");

		FlowPanel userCredentials = new FlowPanel();
		userCredentials.add(userLabel);
		userCredentials.add(username);

		FlowPanel passwordCredentials = new FlowPanel();
		passwordCredentials.add(passwordLabel);
		passwordCredentials.add(password);

		FlowPanel credentialsContainer = new FlowPanel();

		credentialsContainer.add(userCredentials);
		credentialsContainer.add(passwordCredentials);

		userLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		userLabel.setWidth("20%");

		passwordLabel.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		passwordLabel.setWidth("20%");

		this.setWidget(credentialsContainer);
	}

	@Override
	public HasValueChangeHandlers<String> getHasUserCredentialChanged() {
		return username;
	}

	@Override
	public HasValueChangeHandlers<String> getHasPasswordCredentialChanged() {
		return password;
	}

	@Override
	public HasClickHandlers getHasLoginRequestClickedHandlers() {
		return loginButton;
	}

	@Override
	public boolean isVisible() {
		return super.isVisible();
	}
}
