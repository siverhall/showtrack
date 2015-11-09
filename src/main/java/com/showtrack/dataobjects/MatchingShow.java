package com.showtrack.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MatchingShow implements Serializable{
    private int id;
    private String name;

    public MatchingShow() {}

    public MatchingShow(int id) {
        this.id = id;
    }

    public MatchingShow(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
