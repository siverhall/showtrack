package com.showtrack.pages;

import com.showtrack.BaseTest;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.Show;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SeasonPanelTest extends BaseTest {

    public static final int SEASON_NO = 1;
    private SeasonPanel panel;
    private Show show;
    private Episode episode;

    @Before
    public void setUp() throws Exception {
        show = getShow();
        episode = getEpisode();
        when(getEpisodes()).thenReturn(Collections.singletonList(episode));
        panel = getTester().startComponentInPage(new SeasonPanel("season", show, 1));
    }

    @Test
    public void renders() throws Exception {
        assert panel != null;
    }

    @Test
    public void correct_label() throws Exception {
        getTester().assertLabel("season:seasonLabel", "Season 1");
    }

    @Test
    public void assert_list() throws Exception {
        getTester().assertListView("season:episodeList", getEpisodes());
    }

    @Test
    public void seen_checkbox_calls_service() throws Exception {
        getTester().executeAjaxEvent("season:episodeList:0:seen", "onclick");
        verify(episodeService).checkEpisode(eq(episode), any(Boolean.class));
    }

    private List<Episode> getEpisodes() {
        return episodeService.findEpisodes(show, SEASON_NO);
    }

    private Episode getEpisode() {
        Episode episode = new Episode(show, SEASON_NO, 1, "episode", new Date());
        episode.setId(1L);
        return episode;
    }

    private Show getShow() {
        Show show = new Show("show!", "12345");
        show.setId(5);
        return show;
    }


}