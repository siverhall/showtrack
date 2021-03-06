package com.showtrack.services;

import com.google.inject.ImplementedBy;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.Show;

import java.util.List;

/**
 *  Interface for db handling of Episode objects.
 */
public interface EpisodeService {

    List<Episode> findEpisodes(Show show, int season);

    void checkEpisode(Episode episode, Boolean seen);

    Episode getLastSeen(Show show);

    Episode getNextEpisode(Show show);
}
