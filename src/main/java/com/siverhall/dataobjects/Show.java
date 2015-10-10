package com.siverhall.dataobjects;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  Data object for a tv show.
 *  Mapped to database table 'shows'
 */
@Entity
@Table(name = "shows")
public class Show implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Basic
    private String imdb;

    @Basic
    private int noOfSeasons;

    public Show(String name, String imdb) {
        this.name = name;
        this.imdb = imdb;
    }

    public Show() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(int noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }
}
