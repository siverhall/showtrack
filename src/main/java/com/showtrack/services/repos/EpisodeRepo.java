package com.showtrack.services.repos;

import com.google.code.guice.repository.EntityManagerProvider;
import com.showtrack.dataobjects.Episode;
import com.showtrack.dataobjects.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EpisodeRepo extends JpaRepository<Episode, Long>, EntityManagerProvider {

    List<Episode> findByShowAndSeason(Show show, int season);

    List<Episode> findByShowAndSeenOrderByAirDateDesc(Show show, boolean seen);

    List<Episode> findByShowAndAirDateAfterOrderByAirDateAsc(Show show, Date date);
}
