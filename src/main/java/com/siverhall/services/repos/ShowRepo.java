package com.siverhall.services.repos;

import com.google.code.guice.repository.EntityManagerProvider;
import com.siverhall.dataobjects.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<Show, Long>, EntityManagerProvider {

    Show findById(Long id);
}
