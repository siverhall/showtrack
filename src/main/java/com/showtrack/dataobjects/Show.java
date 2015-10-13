package com.showtrack.dataobjects;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  Data object for a tv show.
 *  Mapped to database table 'shows'
 */
@Entity
@Data
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

}
