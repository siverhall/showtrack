package com.siverhall.pages;

import com.siverhall.dataobjects.Show;
import com.siverhall.services.ShowService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.inject.Inject;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private ShowService showService;


	public HomePage(final PageParameters parameters) {
		super(parameters);
		add(new Label("show", new LoadableDetachableModel<String>() {
			@Override
			protected String load() {
				Show suits = showService.findShow("Suits");
				return suits.getName() + " " + suits.getImdb();
			}
		}));
    }
}
