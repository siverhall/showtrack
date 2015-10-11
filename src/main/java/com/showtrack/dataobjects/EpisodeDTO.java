package com.showtrack.dataobjects;

import java.util.Date;

/**
 *  Data Transfer Object that is being used by the Jackson ObjectMapper while retrieving data from the REST Api.
 */
public class EpisodeDTO {

    private int number;
    private String title;
    private int season;
    private Date release_date;
    private ShowDTO show;

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public int getSeason() {
        return season;
    }

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    public Date getRelease_date() {
        return release_date;
    }
}
