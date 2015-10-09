package com.siverhall.services.repos;

import com.google.code.guice.repository.EntityManagerProvider;
import com.siverhall.dataobjects.Episode;
import com.siverhall.dataobjects.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepo extends JpaRepository<Episode, Long>, EntityManagerProvider {

    List<Episode> findByShowAndSeason(Show show, int season);
}