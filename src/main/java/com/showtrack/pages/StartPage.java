package com.showtrack.pages;

import com.showtrack.dataobjects.*;
import com.showtrack.json.ShowProvider;
import com.showtrack.services.EpisodeApi;
import com.showtrack.services.EpisodeService;
import com.showtrack.services.ShowService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.select2.Select2Choice;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  Start page of the application
 */
public class StartPage extends BasePage {

    @Inject
    private ShowService showService;
    @Inject
    private EpisodeApi episodeAPI;
    @Inject
    private EpisodeService episodeService;

    public StartPage(PageParameters parameters) {
        super(parameters);

        add(new FeedbackPanel("feedback"));
        getListOfCurrentShows();
        add(new AddShowForm("searchForm"));

    }

    /**
     *  Display all shows currently available for the user.
     */
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

                item.add(new Label("lastSeen", getLastSeen(item.getModel())));
                item.add(new Label("nextEpisode", getNextEpisode(item.getModel())));
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisible(!getList().isEmpty());
            }
        };

        add(list);
    }

    private IModel<String> getLastSeen(IModel<Show> model) {
        Episode lastSeen = episodeService.getLastSeen(model.getObject());
        return lastSeen == null ? new StringResourceModel("noSeen") :
                new StringResourceModel("lastSeen", Model.of(lastSeen));
    }

    private IModel<String> getNextEpisode(IModel<Show> model) {
        Episode next = episodeService.getNextEpisode(model.getObject());
        return next == null ? new StringResourceModel("noDate") :
                Model.of(formatDate(next.getAirDate()));
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

    /**
     *  Form for adding new shows to watch list through EpGuides API lookup.
     */
    private class AddShowForm extends Form<String> {

        private final Select2Choice<MatchingShow> autoComplete;

        public AddShowForm(String id) {
            super(id);
            autoComplete = new Select2Choice<>("autoComplete", new Model<MatchingShow>(), new ShowProvider(episodeAPI));
            autoComplete.setRequired(true);
            add(autoComplete);
        }

        @Override
        protected void onSubmit() {
            boolean result = episodeAPI.importShow(autoComplete.getConvertedInput().getId());
            if (result) {
                success(getString("submitted"));
            } else {
                error(getString("failed"));
            }
        }

    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
