package com.showtrack.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.EpisodeDTO;
import com.showtrack.dataobjects.Show;
import com.showtrack.dataobjects.ShowDTO;
import com.showtrack.services.repos.EpisodeRepo;
import com.showtrack.services.repos.ShowRepo;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *  Implementations of remote API connected methods
 */
public class EpisodeApiServiceImpl implements EpisodeApiService {

    @Inject
    private ShowRepo showRepo;
    @Inject
    private EpisodeRepo episodeRepo;

    private String apiURL;
    private String apiKey;
    private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER
            = new com.fasterxml.jackson.databind.ObjectMapper();

    @Inject
    public void setApiURL(@Named("apiURL") String apiURL) {
        this.apiURL = apiURL;
    }

    @Inject
    public void setApiKEY(@Named("apiKey") String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     *  Connects to EpGuides API at specified remote url and search for TV shows with the
     *  specified name. Stores both Show and Episode objects if successful.
     *
     */
    @Override
    public boolean findShow(String name) {
        try {
            HttpResponse<JsonNode> showInfo = getResponse(apiURL + name + "/info");
            ShowDTO result = MAPPER.readValue(showInfo.getRawBody(), ShowDTO.class);

            if (showRepo.findByName(result.getTitle()) != null) {
                return false; // Show already exists
            }

            Show show = showRepo.save(new Show(result.getTitle(), result.getImdb_id()));

            HttpResponse<JsonNode> episodes = getResponse(apiURL + name);
            saveEpisodeInfo(episodes, show);

        } catch (UnirestException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *  The Response from the API, returned as a json string
     */
    private HttpResponse<JsonNode> getResponse(String url) throws UnirestException {
        return Unirest.get(url)
                .header("X-Mashape-Key", apiKey)
                .header("Accept", "application/json")
                .asJson();
    }

    /**
     *  Maps the json response from the EpGuides API to EpisodeDTO-objects that we save to our own internal Episode objects.
     *  HashMap<String,List<EpisodeDTO> is being used because of odd json format from the API.
     */
    private void saveEpisodeInfo(HttpResponse<JsonNode> response, Show show) throws IOException {
        HashMap<String, List<EpisodeDTO>> allSeasons = MAPPER.readValue(response.getRawBody(),
                new TypeReference<HashMap<String, List<EpisodeDTO>>>() {});
        for (List<EpisodeDTO> season : allSeasons.values()) {
            for (EpisodeDTO episode : season) {
                episodeRepo.save(new Episode(show, episode.getSeason(),
                        episode.getNumber(), episode.getTitle(), episode.getRelease_date()));
            }
        }
        show.setNoOfSeasons(allSeasons.values().size());
        showRepo.save(show);
    }

}
