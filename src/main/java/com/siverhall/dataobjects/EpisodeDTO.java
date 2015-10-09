package com.siverhall.dataobjects;

import java.util.Date;

public class EpisodeDTO {

    private int number;
    private String title;
    private int season;
    private Date release_date;
    private ShowDTO show;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeason() {
        return season;
    }


    public void setSeason(int season) {
        this.season = season;
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

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
}
