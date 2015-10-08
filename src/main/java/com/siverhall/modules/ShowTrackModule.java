package com.siverhall.modules;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.siverhall.ShowTrackApp;
import org.apache.wicket.protocol.http.WebApplication;

public class ShowTrackModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(new JpaPersistModule("manager1"));
        filter("/*").through(PersistFilter.class);
        bind(WebApplication.class).to(ShowTrackApp.class);
    }
}
