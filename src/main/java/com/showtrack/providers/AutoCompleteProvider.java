package com.showtrack.providers;

import com.showtrack.dataobjects.ShowWrapper;
import com.showtrack.services.EpisodeApiService;
import org.wicketstuff.select2.Response;
import org.wicketstuff.select2.TextChoiceProvider;

import javax.inject.Inject;
import java.util.Collection;

public class AutoCompleteProvider extends TextChoiceProvider<ShowWrapper> {

    private EpisodeApiService service;

    public AutoCompleteProvider(EpisodeApiService service) {
        this.service = service;
    }

    @Override
    protected String getDisplayText(ShowWrapper wrapper) {
        return wrapper.getShow().getName();
    }

    @Override
    protected Object getId(ShowWrapper wrapper) {
        return wrapper.getShow().getName();
    }

    @Override
    public void query(String s, int i, Response<ShowWrapper> response) {
        System.out.println("search: " + s);
        response.addAll(service.findShow(s));
    }

    @Override
    public Collection<ShowWrapper> toChoices(Collection<String> collection) {
        return null;
    }
}
