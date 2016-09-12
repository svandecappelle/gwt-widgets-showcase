package com.mizore.gwt.gwtshowcase.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author svandecappelle
 *
 *         The user information used to connect to an application.
 */
public class UserCredentials implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6840596006115437755L;

	private String username;
	private String password;

	/**
	 * Default constructor
	 */
	public UserCredentials() {
	}

	/**
	 * Constructor using fields.
	 * 
	 * @param username
	 * @param password
	 */
	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Set the username.
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
