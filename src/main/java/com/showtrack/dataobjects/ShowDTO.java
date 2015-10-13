package com.showtrack.dataobjects;

import lombok.Getter;

/**
 *  Data Transfer Object that is being used by the Jackson ObjectMapper while retrieving data from the REST Api.
 */
@Getter
public class ShowDTO {
    private String title;
    private String imdb_id;
    private String epguide_name;
}
