package com.showtrack.dataobjects;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  Data object for a single episode of a show.
 *  Mapped to database table 'episodes'
 */
@Entity
@Data
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

}
