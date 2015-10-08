package com.siverhall;

import com.siverhall.pages.ShowPage;
import com.siverhall.pages.StartPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class ShowTrackApp extends WebApplication
{

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return StartPage.class;
	}


	@Override
	public void init()
	{
		super.init();

        mountPage("home", StartPage.class);
        mountPage("show", ShowPage.class);
	}
}
