package com.showtrack.pages;

import com.google.inject.Inject;
import com.showtrack.dataobjects.Show;
import com.showtrack.services.ShowService;
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
        addSeasonPanels(show);

    }

    /**
     *  Adding a SeasonPanel with all episode info for each season available for the current show.
     */
    private void addSeasonPanels(Show show) {
        RepeatingView seasons = new RepeatingView("seasonList");
        for (int i = 1; i <= show.getNoOfSeasons(); i++) {
            seasons.add(new SeasonPanel(seasons.newChildId(), show, i));
        }
        add(seasons);
    }

}
