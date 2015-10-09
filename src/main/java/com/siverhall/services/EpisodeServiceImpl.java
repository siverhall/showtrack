package com.siverhall.services;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.repos.EpisodeRepo;

import javax.persistence.EntityManager;
import java.util.List;

public class EpisodeServiceImpl implements EpisodeService {

    @Inject
    private EpisodeRepo episodeRepo;

    @Override
    public List<Episode> findEpisodes(Show show, int season) {
        return episodeRepo.findByShowAndSeason(show, season);
    }

    @Override
    public void checkEpisode(Episode episode, Boolean seen) {
        episode.setSeen(seen);
        episodeRepo.save(episode);
    }
}
