package com.showtrack.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showtrack.dataobjects.MatchingShow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowMapper {

    private List<MatchingShow> shows;

    public ShowMapper(InputStream json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        shows = new ArrayList<>();

        visitChildren(root);
    }

    private void visitChildren(JsonNode root) {
        for (JsonNode child : root) {
            JsonNode show = child.path("show");
            int id = show.path("id").asInt();
            String name = show.path("name").asText();
            MatchingShow matchingShow = new MatchingShow(id, name);
            shows.add(matchingShow);
        }
    }

    public List<MatchingShow> getShows() {
        return shows;
    }
}
