package com.siverhall.api;

public class EpisodeDTO {

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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    private int number;
    private String title;
    private int season;
    private String release_date;
    private ShowDTO show;

}
