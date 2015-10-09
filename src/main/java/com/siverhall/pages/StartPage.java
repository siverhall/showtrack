package com.siverhall.pages;

import com.siverhall.dataobjects.Show;
import com.siverhall.services.EpisodeApiService;
import com.siverhall.services.ShowService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.inject.Inject;
import java.util.List;

/**
 *  Start page of the application
 */
public class StartPage extends BasePage {

    @Inject
    private ShowService showService;
    @Inject
    private EpisodeApiService epguidesAPI;

    public StartPage(PageParameters parameters) {
        super(parameters);

        add(new FeedbackPanel("feedback"));
        getListOfCurrentShows();
        add(new AddShowForm("searchForm"));

    }

    private void getListOfCurrentShows() {
        final ListView<Show> list = new ListView<Show>("currentShows", getCurrentShows()) {
            @Override
            protected void populateItem(ListItem<Show> item) {
                Show show = item.getModelObject();
                PageParameters pp = new PageParameters();
                pp.add("show", show.getId());
                BookmarkablePageLink<Show> link = new BookmarkablePageLink<>("showLink", ShowPage.class, pp);
                item.add(link);
                link.add(new Label("showName", show.getName()));
            }
        };
        add(list);
        add(new Label("noShows", "You are currently not following any shows.") {
            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisibilityAllowed(list.getList().isEmpty());
            }
        });
    }

    /**
     *  Returns the current shows existing in the database.
     */
    private LoadableDetachableModel<List<Show>> getCurrentShows() {
        return new LoadableDetachableModel<List<Show>>() {
            @Override
            protected List<Show> load() {
                return showService.getCurrentShows();
            }
        };
    }

    private class AddShowForm extends Form<String> {

        private final TextField<String> searchString;

        public AddShowForm(String id) {
            super(id);
            searchString = new RequiredTextField<>("searchString", Model.of(""));
            add(searchString);
        }

        @Override
        protected void onSubmit() {
            boolean found = epguidesAPI.findShow(searchString.getModelObject());
            if (found) {
                success("Found show and it has been added to your show list.");
            } else {
                error("Couldn't find show or it is already available in your show list.");
            }
        }

    }
}
