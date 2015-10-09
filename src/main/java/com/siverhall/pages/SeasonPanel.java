package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.EpisodeService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

/**
 *  Wicket Panel that holds info about a specified season of the Show provided in the constructor.
 */
public class SeasonPanel extends Panel {

    @Inject
    private EpisodeService episodeService;

    private final Show show;
    private int season;

    public SeasonPanel(String id, Show show, int season) {
        super(id);

        this.show = show;
        this.season = season;
        add(new Label("seasonLabel", "Season " + season));
        add(new EpisodeListView("episodeList", getEpisodes()));
    }

    private LoadableDetachableModel<List<Episode>> getEpisodes() {
        return new LoadableDetachableModel<List<Episode>>() {
            @Override
            protected List<Episode> load() {
                return episodeService.findEpisodes(show, season);
            }
        };
    }

    private class EpisodeListView extends ListView<Episode> {

        public EpisodeListView(String episodeList, LoadableDetachableModel<List<Episode>> model) {
            super(episodeList, model);
        }

        @Override
        protected void populateItem(ListItem<Episode> item) {
            item.add(new Label("episode", new PropertyModel<>(item.getModel(), "episode")));
            item.add(new Label("title", new PropertyModel<>(item.getModel(), "title")));
        }


    }
}
