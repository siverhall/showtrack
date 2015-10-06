package com.siverhall.dataobjects;

import javax.persistence.*;

@Entity
@Table(name = "seen_episodes")
public class SeenEpisode {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Show show;

    private int season;
    private int episode;

    public Long getId() {
        return id;
    }


    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
}
