package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.ShowService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *  Web page showing all episodes of the show specified.
 */
public class ShowPage extends BasePage {

    @Inject
    private ShowService showService;

    /**
     *  Page constructor with parameters specifying the selected shows id.
     */
    public ShowPage(PageParameters parameters) {
        super(parameters);
        final Long showId = parameters.get("show").toLongObject();
        Show show = showService.findById(showId);

        add(new Label("title", show.getName()));
        add(new ExternalLink("imdb", "http://www.imdb.com/title/" + show.getImdb()));
        addSeasonPanels(show);

    }

    private void addSeasonPanels(Show show) {
        RepeatingView seasons = new RepeatingView("seasonList");
        for (int i = 1; i <= show.getNoOfSeasons(); i++) {
            seasons.add(new SeasonPanel(seasons.newChildId(), show, i));
        }
        add(seasons);
    }

}
