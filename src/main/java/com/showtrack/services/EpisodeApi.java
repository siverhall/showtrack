package com.showtrack.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.showtrack.dataobjects.MatchingShow;

import java.io.IOException;
import java.util.List;

/**
 *  Interface for methods connecting to remote API.
 */
public interface EpisodeApi {
    List<MatchingShow> find(String name);

    boolean importShow(int id);
}
