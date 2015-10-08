package com.siverhall;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.siverhall.modules.ShowTrackModule;
import org.apache.wicket.guice.GuiceWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

public class GuiceBootstrap extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ShowTrackModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        Map<String,String> params = new HashMap<>();
                        params.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
                        params.put(WicketFilter.APP_FACT_PARAM, GuiceWebApplicationFactory.class.getName());
                        params.put("injectorContextAttribute", Injector.class.getName());
                        filter("/*").through(new WicketFilter(), params);
                    }
                });
    }
}
