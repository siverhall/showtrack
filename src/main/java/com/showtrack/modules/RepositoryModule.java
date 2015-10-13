package com.showtrack.modules;

import com.google.code.guice.repository.configuration.JpaRepositoryModule;
import com.google.code.guice.repository.configuration.RepositoryBinder;
import com.showtrack.services.repos.EpisodeRepo;
import com.showtrack.services.repos.ShowRepo;

/**
 *  Guice module that binds all repositories to the current persistence unit specified in
 *  persistence.xml ("hibernate-manager"). Manual addition of each repository is mandatory.
 */
public class RepositoryModule extends JpaRepositoryModule {
    public RepositoryModule() {
        super("tomcat-hibernate");
    }

    @Override
    protected void bindRepositories(RepositoryBinder binder) {
        binder.bind(ShowRepo.class).to("tomcat-hibernate");
        binder.bind(EpisodeRepo.class).to("tomcat-hibernate");
    }
}
