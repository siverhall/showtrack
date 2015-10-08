package com.siverhall.pages;

import com.siverhall.dataobjects.Show;
import com.siverhall.services.ShowService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.inject.Inject;
import java.util.List;

public class StartPage extends BasePage {

    @Inject
    private ShowService showService;

    public StartPage(PageParameters parameters) {
        super(parameters);

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

    private LoadableDetachableModel<List<Show>> getCurrentShows() {
        return new LoadableDetachableModel<List<Show>>() {
            @Override
            protected List<Show> load() {
                return showService.getCurrentShows();
            }
        };
    }
}
