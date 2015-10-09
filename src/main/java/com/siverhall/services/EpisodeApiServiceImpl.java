package com.siverhall.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.EpisodeDTO;
import com.siverhall.dataobjects.Show;
import com.siverhall.dataobjects.ShowDTO;
import com.siverhall.services.repos.EpisodeRepo;
import com.siverhall.services.repos.ShowRepo;

import javax.inject.Inject;
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

    public static final String EPGUIDES_BASE_URL = "https://frecar-epguides-api-v1.p.mashape.com/";
    public static final String API_KEY = "FjBJLAnmuzmshbdX0fQnsf3FtcSCp15ozkIjsncujQK21qrRMV";
    private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER
            = new com.fasterxml.jackson.databind.ObjectMapper();

    /**
     *  Connects to EpGuides API at specified remote url and search for TV shows with the
     *  specified name. Stores both Show and Episode objects if successful.
     *
     */
    @Override
    public boolean findShow(String name) {
        try {
            HttpResponse<JsonNode> showInfo = getResponse(EPGUIDES_BASE_URL + name + "/info");
            ShowDTO result = MAPPER.readValue(showInfo.getRawBody(), ShowDTO.class);

            if (showRepo.findByName(result.getTitle()) != null) {
                System.out.println("Show already exists");
                return false;
            }

            Show show = showRepo.save(new Show(result.getTitle(), result.getImdb_id()));

            HttpResponse<JsonNode> episodes = getResponse(EPGUIDES_BASE_URL + name);
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
                .header("X-Mashape-Key", API_KEY)
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
