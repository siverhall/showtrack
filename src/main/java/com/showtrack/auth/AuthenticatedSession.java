package com.showtrack.auth;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AuthenticatedSession extends AuthenticatedWebSession {

    public AuthenticatedSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String user, String pass) {
        return user.equals(pass);
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}
