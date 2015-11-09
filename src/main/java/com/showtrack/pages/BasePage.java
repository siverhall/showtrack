package com.showtrack.pages;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * 	Abstract base page that the other web pages inherit from that holds the main design.
 */
public abstract class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage(final PageParameters parameters) {
		super(parameters);
		add(new BookmarkablePageLink<Void>("homeLink", getApplication().getHomePage()));
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
		if (!AuthenticatedWebSession.get().isSignedIn()) {
			app.restartResponseAtSignInPage();
		}
	}
}
