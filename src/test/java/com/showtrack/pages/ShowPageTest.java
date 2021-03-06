package com.showtrack.pages;

import com.showtrack.BaseTest;
import com.showtrack.dataobjects.Show;
import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ShowPageTest extends BaseTest {

    public static final Long SHOW_ID = 6L;
    public static final int SEASONS = 5;

    @Before
    public void setUp() throws Exception {
        when(showService.findById(SHOW_ID)).thenReturn(getShow());
        getTester().startPage(ShowPage.class, getParameters());
        login("secret", "secret");
    }

    @Test
    public void renders() throws Exception {
        getTester().assertRenderedPage(ShowPage.class);
    }

    @Test
    public void one_repeating_view_for_each_season() throws Exception {
        RepeatingView seasons = (RepeatingView) getTester().getLastRenderedPage().get("seasonList");
        Iterator<Component> iterator = seasons.iterator();
        int seasonPanels = 0;
        while(iterator.hasNext()) {
            seasonPanels++;
            iterator.next();
        }

        assertEquals(seasonPanels, SEASONS);
    }

    private Show getShow() {
        Show show = new Show("show!", "Running", 5, 5);
        show.setNoOfSeasons(SEASONS);
        return show;
    }

    private PageParameters getParameters() {
        PageParameters pp = new PageParameters();
        pp.add("show", SHOW_ID);
        return pp;
    }
}