package com.showtrack;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.showtrack.services.EpisodeApi;
import com.showtrack.services.EpisodeService;
import com.showtrack.services.ShowService;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest {

    private WicketTester tester;

    @Mock
    protected ShowService showService;
    @Mock
    protected EpisodeApi episodeApi;
    @Mock
    protected EpisodeService episodeService;

    protected final WicketTester getTester() {
        return tester;
    }

    @Before
    public void setUpApplication() throws Exception {
        final Injector injector = Guice.createInjector(new TestModule());

        ShowTrackApp application = new ShowTrackApp();
        application.getComponentInstantiationListeners().add(
                new GuiceComponentInjector(application, injector));
        tester = new WicketTester(application);
    }


    private class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(ShowService.class).toInstance(showService);
            bind(EpisodeApi.class).toInstance(episodeApi);
            bind(EpisodeService.class).toInstance(episodeService);
        }
    }
}
