package com.siverhall.modules;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.siverhall.ShowTrackApp;
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

        Properties properties = loadProperties();
        bindConstant().annotatedWith(Names.named("apiURL")).to(properties.getProperty("apiURL"));
        bindConstant().annotatedWith(Names.named("apiKey")).to(properties.getProperty("apiKey"));

        bind(WebApplication.class).to(ShowTrackApp.class);
    }

    private Properties loadProperties() {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("apiDetails.properties"));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
