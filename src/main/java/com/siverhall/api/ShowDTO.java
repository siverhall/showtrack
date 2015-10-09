package com.siverhall.api;

public class ShowDTO {
        private String title;
        private String imdb_id;
        private String epguide_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImdb_id() {
            return imdb_id;
        }

        public void setImdb_id(String imdb_id) {
            this.imdb_id = imdb_id;
        }

        public String getEpguide_name() {
            return epguide_name;
        }

        public void setEpguide_name(String epguide_name) {
            this.epguide_name = epguide_name;
        }

}
