package com.siverhall.modules;

import com.google.inject.servlet.ServletModule;
import com.siverhall.ShowTrackApp;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *  Main module for the application, loaded via GuiceBootstrap (listener specified in web.xml)
 */
public class ShowTrackModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(new RepositoryModule());
        bind(WebApplication.class).to(ShowTrackApp.class);
    }
}
