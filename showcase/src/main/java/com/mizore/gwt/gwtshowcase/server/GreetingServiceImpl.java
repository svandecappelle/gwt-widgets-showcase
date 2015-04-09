package com.mizore.gwt.gwtshowcase.server;

import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mizore.gwt.gwtshowcase.client.GreetingService;
import com.mizore.gwt.gwtshowcase.shared.AuthenticateException;
import com.mizore.gwt.gwtshowcase.shared.FieldVerifier;
import com.mizore.gwt.gwtshowcase.shared.UserCredentials;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private static final Logger logger = Logger.getLogger("AuthenticationService");

    public String greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back
            // to
            // the client.
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script
        // vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html
     *            the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    @Override
    public boolean isAuthenticated() throws AuthenticateException {
        return getThreadLocalRequest().getSession().getAttributeNames().hasMoreElements();
    }

    @Override
    public boolean authenticate(UserCredentials userCredential) throws AuthenticateException {
        if ("demo".equals(userCredential.getUsername()) && "demo".equals(userCredential.getPassword())) {
            logger.info(userCredential.getUsername().concat(" is now authenticated"));
            getThreadLocalRequest().getSession().setAttribute("connectedUser", userCredential.getUsername());
            return true;
        }
        logger.info(userCredential.getUsername().concat(" fires error on authentication"));
        return false;
    }
}
