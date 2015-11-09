package com.showtrack.json;

import com.showtrack.dataobjects.MatchingShow;
import com.showtrack.services.EpisodeApi;
import org.wicketstuff.select2.Response;
import org.wicketstuff.select2.TextChoiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowProvider extends TextChoiceProvider<MatchingShow> {

    private EpisodeApi service;

    public ShowProvider(EpisodeApi service) {
        this.service = service;
    }

    @Override
    protected String getDisplayText(MatchingShow show) {
        return show.getName();
    }

    @Override
    protected Object getId(MatchingShow show) {
        return show.getId();
    }

    @Override
    public void query(String searchTerm, int i, Response<MatchingShow> response) {
        response.addAll(service.find(searchTerm));
    }

    @Override
    public Collection<MatchingShow> toChoices(Collection<String> ids) {
        List<MatchingShow> choices = new ArrayList<>(ids.size());
        for (String id : ids) {
            choices.add(new MatchingShow(Integer.valueOf(id)));
        }
        return choices;
    }
}
