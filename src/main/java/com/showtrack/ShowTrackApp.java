package com.showtrack;

import com.showtrack.auth.AuthenticatedSession;
import com.showtrack.pages.LoginPage;
import com.showtrack.pages.ShowPage;
import com.showtrack.pages.StartPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;

/**
 *  The starting point of the application. Decides which page to go to as homepage
 *  and handles Wicket-specific setup
 */
public class ShowTrackApp extends AuthenticatedWebApplication
{

	public static final String JQUERY = "https://code.jquery.com/jquery-2.1.4.min.js";

	@Override
	public void init()
	{
		super.init();
		getJavaScriptLibrarySettings().setJQueryReference(new UrlResourceReference(Url.parse(JQUERY)));

		mountPage("home", StartPage.class);
		mountPage("show", ShowPage.class);
		mountPage("login", LoginPage.class);
	}

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return StartPage.class;
	}




	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AuthenticatedSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}
}
