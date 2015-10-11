package com.showtrack.services.repos;

import com.google.code.guice.repository.EntityManagerProvider;
import com.showtrack.dataobjects.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<Show, Long>, EntityManagerProvider {

    Show findById(Long id);
    Show findByName(String name);
}
