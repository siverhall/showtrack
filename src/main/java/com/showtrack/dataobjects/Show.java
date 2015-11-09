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
    private String status;

    @Basic
    private int showId;

    @Basic
    private int noOfSeasons;

    public Show(String name, String status, int showId, int noOfSeasons) {
        this.name = name;
        this.status = status;
        this.showId = showId;
        this.noOfSeasons = noOfSeasons;
    }

    public Show() {
    }

}
