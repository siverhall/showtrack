package com.siverhall.pages;

import com.google.inject.Inject;
import com.siverhall.dataobjects.User;
import com.siverhall.services.UserService;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
		add(new Label("userLabel", new LoadableDetachableModel<String>() {
			@Override
			protected String load() {
				User user = userService.getUser("emil");
				return user.getUsername() + "("+user.getEmail()+")";
			}
		}));

    }
}
