package com.showtrack.dataobjects;

import lombok.Getter;

import java.util.Date;

/**
 *  Data Transfer Object that is being used by the Jackson ObjectMapper while retrieving data from the REST Api.
 */
@Getter
public class EpisodeDTO {

    private String name;
    private int number;
    private int season;
    private Date airdate;

}
