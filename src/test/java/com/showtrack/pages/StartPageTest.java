package com.showtrack.pages;

import com.showtrack.BaseTest;
import com.showtrack.dataobjects.Show;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartPageTest extends BaseTest
{

    private StartPage page;

    @Before
    public void setUp() throws Exception {
        when(showService.getCurrentShows()).thenReturn(Collections.singletonList(getShow()));
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
