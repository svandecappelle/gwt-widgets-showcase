package com.mizore.gwt.gwtshowcase.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mizore.gwt.gwtshowcase.shared.AuthenticateException;
import com.mizore.gwt.gwtshowcase.shared.UserCredentials;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;

	boolean isAuthenticated() throws AuthenticateException;

	boolean authenticate(UserCredentials userCredential) throws AuthenticateException;
}
