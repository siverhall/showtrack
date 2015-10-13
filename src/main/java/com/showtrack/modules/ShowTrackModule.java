package com.showtrack.modules;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.showtrack.ShowTrackApp;
import com.showtrack.services.*;
import org.apache.wicket.protocol.http.WebApplication;

import java.io.IOException;
import java.util.Properties;

/**
 *  Main module for the application, loaded via GuiceBootstrap (listener specified in web.xml)
 *  Loads property file that holds api-details.
 */
public class ShowTrackModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(new RepositoryModule());
        bind(ShowService.class).to(ShowServiceImpl.class);
        bind(EpisodeService.class).to(EpisodeServiceImpl.class);
        bind(EpisodeApiService.class).to(EpisodeApiServiceImpl.class);

        setupProperties();

        bind(WebApplication.class).to(ShowTrackApp.class);
    }

    /**
     *  Load details that is needed to connect to remote REST Api (url and api-key)
     */
    private Properties setupProperties() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("apiDetails.properties"));
            bindConstant().annotatedWith(Names.named("apiURL")).to(prop.getProperty("apiURL"));
            bindConstant().annotatedWith(Names.named("apiKey")).to(prop.getProperty("apiKey"));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
