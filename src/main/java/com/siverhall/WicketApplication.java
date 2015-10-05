package com.siverhall;

import com.siverhall.modules.ShowTrackModule;
import com.siverhall.pages.HomePage;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketApplication extends WebApplication
{

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}


	@Override
	public void init()
	{
		super.init();

		getComponentInstantiationListeners().add(
				new GuiceComponentInjector(this, new ShowTrackModule()));
	}
}
