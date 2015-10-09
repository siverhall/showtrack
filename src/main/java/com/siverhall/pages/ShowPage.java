package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.EpisodeApiService;
import com.siverhall.services.ShowService;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *  Web page showing all episodes of the show specified.
 */
public class ShowPage extends BasePage {

    @Inject
    private ShowService showService;
    @Inject
    private EpisodeApiService epguides;

    /**
     *  Page constructor with parameters specifying the selected shows id.
     */
    public ShowPage(PageParameters parameters) {
        super(parameters);
        Long showId = parameters.get("show").toLongObject();
        System.out.println("id: " + showId);
        Show show = showService.findById(showId);
        System.out.println(epguides.findShow(show.getName()));

    }
}
