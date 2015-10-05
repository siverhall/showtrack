package com.siverhall.modules;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class ShowTrackModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        install(new JpaPersistModule("manager1"));
        filter("/*").through(PersistFilter.class);
    }
}
