package com.siverhall.services.repos;

import com.google.code.guice.repository.EntityManagerProvider;
import com.siverhall.dataobjects.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepo extends JpaRepository<Episode, Long>, EntityManagerProvider {

}
