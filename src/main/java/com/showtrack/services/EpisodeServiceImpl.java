package com.showtrack.services;

import com.google.inject.Inject;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.Show;
import com.showtrack.services.repos.EpisodeRepo;

import java.util.Date;
import java.util.List;

/**
 *  Implementation of the EpisodeService interface
 */
public class EpisodeServiceImpl implements EpisodeService {

    @Inject
    private EpisodeRepo episodeRepo;

    /**
     *  Find all episodes for a specified season of a show.
     */
    @Override
    public List<Episode> findEpisodes(Show show, int season) {
        return episodeRepo.findByShowAndSeason(show, season);
    }

    /**
     *  Marks an episode as seen or not.
     */
    @Override
    public void checkEpisode(Episode episode, Boolean seen) {
        episode.setSeen(seen);
        episodeRepo.save(episode);
    }

    @Override
    public Episode getLastSeen(Show show) {
        List<Episode> seenEpisodes = episodeRepo.findByShowAndSeenOrderByAirDateDesc(show, true);
        return seenEpisodes.isEmpty() ? null : seenEpisodes.get(0);
    }

    @Override
    public Episode getNextEpisode(Show show) {
        Date now = new Date();
        List<Episode> found = episodeRepo.findByShowAndAirDateAfterOrderByAirDateAsc(show, now);
        return found.isEmpty() ? null : found.get(0);
    }
}
