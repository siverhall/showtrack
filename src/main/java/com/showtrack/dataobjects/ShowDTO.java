package com.showtrack.dataobjects;

import lombok.Getter;

/**
 *  Data Transfer Object that is being used by the Jackson ObjectMapper while retrieving data from the REST Api.
 */
@Getter
public class ShowDTO {
    private int id;
    private String name;
    private String status;

    private Wrapper _embedded;

    @Getter
    public class Wrapper {
        private PreviousEpisode previousepisode;

        public class PreviousEpisode {
            private int season;

            public int getSeason() {
                return season;
            }
        }
    }
}
