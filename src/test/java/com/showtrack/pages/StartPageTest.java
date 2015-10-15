package com.showtrack.pages;

import com.showtrack.BaseTest;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.Show;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartPageTest extends BaseTest
{

    private StartPage page;
    private Show show;

    @Before
    public void setUp() throws Exception {
        show = getShow();
        when(showService.getCurrentShows()).thenReturn(Collections.singletonList(show));
        when(episodeApiService.findShow(anyString())).thenReturn(true);
        page = getTester().startPage(StartPage.class);
    }

    @Test
	public void homepageRendersSuccessfully()
	{
		getTester().assertRenderedPage(StartPage.class);
	}

    @Test
    public void form_submission_requires_input() throws Exception {
        FormTester formTester = getTester().newFormTester("searchForm");
        formTester.submit();
        getTester().assertErrorMessages(page.getString("searchString.Required"));
    }

    @Test
    public void form_submission_calls_api_service() throws Exception {
        String searchString = "show!";
        submitForm(searchString);
        assertThat(getTester().getMessages(FeedbackMessage.SUCCESS), hasItem(page.getString("submitted")));
        verify(episodeApiService).findShow(searchString);
    }

    @Test
    public void failed_form_submission_prints_error_message() throws Exception {
        when(episodeApiService.findShow(anyString())).thenReturn(false);
        submitForm("show!");
        assertThat(getTester().getMessages(FeedbackMessage.ERROR), hasItem(page.getString("failed")));
    }

    @Test
    public void shows_correct_label_based_on_last_seen_episode() throws Exception {
        Episode last = new Episode(show, 1, 1, "Ep", new Date());
        when(episodeService.getLastSeen(show)).thenReturn(last);
        StartPage page = getTester().startPage(StartPage.class);

        getTester().assertLabel("currentShows:0:lastSeen", page.getString("lastSeen", Model.of(last)));
    }

    @Test
    public void no_episodes_seen_label_is_being_displayed_correctly() throws Exception {
        when(episodeService.getLastSeen(show)).thenReturn(null);
        StartPage page = getTester().startPage(StartPage.class);
        getTester().assertLabel("currentShows:0:lastSeen", page.getString("noSeen"));
    }

    @Test
    public void shows_correct_label_based_on_next_available_episode() throws Exception {
        Date futureDate = new Date();
        Episode next = new Episode(show, 1, 1, "Ep", futureDate);
        when(episodeService.getNextEpisode(show)).thenReturn(next);
        getTester().startPage(StartPage.class);

        getTester().assertLabel("currentShows:0:nextEpisode", new SimpleDateFormat("yyyy-MM-dd").format(futureDate));
    }

    @Test
    public void shows_correct_label_if_no_future_episodes_are_available() throws Exception {
        when(episodeService.getNextEpisode(show)).thenReturn(null);
        StartPage page = getTester().startPage(StartPage.class);

        getTester().assertLabel("currentShows:0:nextEpisode", page.getString("noDate"));
    }


    @Test
    public void list_of_current_shows_is_not_visible_when_empty() throws Exception {
        when(showService.getCurrentShows()).thenReturn(Collections.<Show>emptyList());
        getTester().startPage(StartPage.class);
        getTester().assertInvisible("currentShows");
    }

    private void submitForm(String searchString) {
        FormTester formTester = getTester().newFormTester("searchForm");
        formTester.setValue("searchString", searchString);
        formTester.submit();
    }

    private Show getShow() {
        Show show = new Show("show", "imdb");
        show.setId(1L);
        return show;
    }
}
