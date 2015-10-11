package com.showtrack.dataobjects;

/**
 *  Data Transfer Object that is being used by the Jackson ObjectMapper while retrieving data from the REST Api.
 */
public class ShowDTO {
    private String title;
    private String imdb_id;
    private String epguide_name;

    public String getTitle() {
        return title;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getEpguide_name() {
        return epguide_name;
    }
}
