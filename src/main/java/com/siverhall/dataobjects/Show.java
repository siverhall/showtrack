package com.siverhall.dataobjects;

import javax.persistence.*;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Basic
    private String imdb;

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
}
