package com.siverhall.services;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.repos.EpisodeRepo;

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
}
