package com.showtrack.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.showtrack.dataobjects.ShowWrapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

public class TvMazeImpl implements EpisodeApiService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private String apiURL;

    @Inject
    public void setApiURL(@Named("apiURL") String apiURL) {
        this.apiURL = apiURL;
    }

    @Override
    public List<ShowWrapper> findShow(String name) {
        String clean = name.replace(" ", "+");
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            HttpResponse<JsonNode> showInfo = getResponse(apiURL + clean);
            return MAPPER.readValue(showInfo.getRawBody(), new TypeReference<List<ShowWrapper>>(){});
        } catch (UnirestException | IOException e) {
            return null;
        }
    }

    private HttpResponse<JsonNode> getResponse(String url) throws UnirestException {
        return Unirest.get(url)
                .header("Accept", "application/json")
                .asJson();
    }
}
