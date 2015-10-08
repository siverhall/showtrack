package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.services.ShowService;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ShowPage extends BasePage {

    @Inject
    private ShowService showService;

    public ShowPage(PageParameters parameters) {
        super(parameters);
        System.out.println("id: " + parameters.get("show"));
    }
}
