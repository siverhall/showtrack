package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.services.ShowService;
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
        Long showId = parameters.get("show").toLongObject();
        System.out.println("id: " + showId);
        System.out.println("name: " + showService.findById(showId).getName());
    }
}
