package com.showtrack.dataobjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  Data object for a single episode of a show.
 *  Mapped to database table 'episodes'
 */
@Entity
@Table(name = "episodes")
public class Episode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Show show;

    @Basic(optional = false)
    private int season;

    @Basic(optional = false)
    private int episode;

    @Basic
    private String title;

    private Date releaseDate;

    @Column(name = "seen", nullable = false)
    private boolean seen = false;

    public Episode(Show show, int season, int episode, String title, Date releaseDate) {
        this.show = show;
        this.season = season;
        this.episode = episode;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Episode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate == null ? null : new Date(releaseDate.getTime());
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate == null ? null : new Date(releaseDate.getTime());
    }
}
