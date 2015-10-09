package com.siverhall.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.siverhall.api.EpisodeDTO;
import com.siverhall.api.ShowDTO;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.repos.EpisodeRepo;
import com.siverhall.services.repos.ShowRepo;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EpisodeApiServiceImpl implements EpisodeApiService {

    @Inject
    private ShowRepo showRepo;
    @Inject
    private EpisodeRepo episodeRepo;

    public static final String EPGUIDES_BASE = "https://frecar-epguides-api-v1.p.mashape.com/";
    public static final String API_KEY = "FjBJLAnmuzmshbdX0fQnsf3FtcSCp15ozkIjsncujQK21qrRMV";
    private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER
            = new com.fasterxml.jackson.databind.ObjectMapper();

    @Override
    public Show findShow(String name) {
        try {
            HttpResponse<JsonNode> showInfo = getResponse(EPGUIDES_BASE + name + "/info");
            Show show = saveShowInfo(showInfo);

            HttpResponse<JsonNode> episodes = getResponse(EPGUIDES_BASE + name);
            saveEpisodeInfo(episodes, show);

        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpResponse<JsonNode> getResponse(String url) throws UnirestException {
        return Unirest.get(url)
                .header("X-Mashape-Key", API_KEY)
                .header("Accept", "application/json")
                .asJson();
    }

    private Show saveShowInfo(HttpResponse<JsonNode> response) throws IOException {
        ShowDTO result = MAPPER.readValue(response.getRawBody(), ShowDTO.class);
        return showRepo.save(new Show(result.getTitle(), result.getImdb_id()));
    }

    private void saveEpisodeInfo(HttpResponse<JsonNode> response, Show show) throws IOException {
        HashMap<String, List<EpisodeDTO>> result = MAPPER.readValue(response.getRawBody(),
                new TypeReference<HashMap<String, List<EpisodeDTO>>>() {});
        for (List<EpisodeDTO> season : result.values()) {
            for (EpisodeDTO episode : season) {
                episodeRepo.save(new Episode(show, episode.getSeason(), episode.getNumber(), episode.getTitle()));
            }
        }

    }

}
