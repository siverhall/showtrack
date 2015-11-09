package com.showtrack.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.showtrack.dataobjects.*;
import com.showtrack.json.ShowMapper;
import com.showtrack.services.repos.EpisodeRepo;
import com.showtrack.services.repos.ShowRepo;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

public class TvMazeEpisodeApiImpl implements EpisodeApi {

    @Inject
    private ShowRepo showRepo;
    @Inject
    private EpisodeRepo episodeRepo;
    private String apiURL;

    private final String searchURL = "search/shows?q=";
    private final String showURL = "shows/";
    private final String embedded = "?embed=previousepisode";
    private final ObjectMapper MAPPER = new ObjectMapper();

    @Inject
    public void setApiURL(@Named("apiURL") String apiURL) {
        this.apiURL = apiURL;
    }

    @Override
    public List<MatchingShow> find(String name) {
        String clean = name.replace(" ", "+");
        try {
            HttpResponse<JsonNode> showInfo = getResponse(apiURL + searchURL + clean);
            ShowMapper mapper = new ShowMapper(showInfo.getRawBody());
            return mapper.getShows();
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean importShow(int id) {
        try {
            HttpResponse<JsonNode> response = getResponse(apiURL + showURL + id + embedded);
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ShowDTO showDto = MAPPER.readValue(response.getRawBody(), ShowDTO.class);
            Show show = showRepo.save(new Show(showDto.getName(), showDto.getStatus(),
                    showDto.getId(), showDto.get_embedded().getPreviousepisode().getSeason()));
            importEpisodes(show);
            return true;

        } catch (UnirestException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void importEpisodes(Show show) {
        try {
            HttpResponse<JsonNode> response = getResponse(apiURL + showURL + show.getShowId() + "/episodes");
            List<EpisodeDTO> episodes = MAPPER.readValue(response.getRawBody(), new TypeReference<List<EpisodeDTO>>() {});
            for (EpisodeDTO episode : episodes) {
                episodeRepo.save(new Episode(show, episode.getSeason(), episode.getNumber(), episode.getName(), episode.getAirdate()));
            }
        } catch (UnirestException | IOException e) {
            e.printStackTrace();
        }
    }

    private HttpResponse<JsonNode> getResponse(String url) throws UnirestException {
        return Unirest.get(url)
                .header("Accept", "application/json")
                .asJson();
    }
}
