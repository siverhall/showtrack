package com.siverhall.dataobjects;

import javax.persistence.*;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Show show;

    @Basic(optional = false)
    private int season;

    @Basic(optional = false)
    private int episode;

    @Column(name = "seen", nullable = false)
    private boolean seen = true;

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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
