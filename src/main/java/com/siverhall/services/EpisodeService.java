package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;

import java.util.List;

@ImplementedBy(EpisodeServiceImpl.class)
public interface EpisodeService {

    List<Episode> findEpisodes(Show show, int season);

    void checkEpisode(Episode episode, Boolean seen);
}
