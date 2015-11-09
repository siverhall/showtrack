package com.showtrack;

import com.showtrack.auth.AuthenticatedSession;
import org.apache.wicket.request.Request;

public class TestSession extends AuthenticatedSession {
    public TestSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String user, String pass) {
        return user.equals(pass);
    }
}
