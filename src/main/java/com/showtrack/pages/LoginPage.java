package com.showtrack.pages;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public class LoginPage extends WebPage {

    @SuppressWarnings("unused")
    private String username;
    @SuppressWarnings("unused")
    private String password;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new LoginForm("loginForm", new CompoundPropertyModel<>(this)));
    }

    private class LoginForm extends StatelessForm<LoginPage> {
        public LoginForm(String id, IModel<LoginPage> model) {
            super(id, model);
            add(new RequiredTextField<String>("username"));
            add(new PasswordTextField("password"));
        }

        @Override
        protected void onSubmit() {
            boolean success = AuthenticatedWebSession.get().signIn(username, password);
            if (success) {
                continueToOriginalDestination();
            }
        }
    }
}
