package com.siverhall.pages;

import com.siverhall.dataobjects.Show;
import com.siverhall.services.ShowService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.inject.Inject;
import java.util.List;

/**
 *  Start page of the application
 */
public class StartPage extends BasePage {

    @Inject
    private ShowService showService;

    public StartPage(PageParameters parameters) {
        super(parameters);

        getListOfCurrentShows();
        add(new NewShowForm("showForm"));

    }

    private void getListOfCurrentShows() {
        add(new ListView<Show>("currentShows", getCurrentShows()) {
            @Override
            protected void populateItem(ListItem<Show> item) {
                Show show = item.getModelObject();
                PageParameters pp = new PageParameters();
                pp.add("show", show.getId());
                BookmarkablePageLink<Show> link = new BookmarkablePageLink<>("showLink", ShowPage.class, pp);
                item.add(link);
                link.add(new Label("showName", show.getName()));
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

    private class NewShowForm extends Form<Show> {

        public NewShowForm(String id) {
            super(id);
            add(new TextField<String>("formName"));
        }
    }
}
