package com.siverhall.modules;

import com.google.code.guice.repository.configuration.JpaRepositoryModule;
import com.google.code.guice.repository.configuration.RepositoryBinder;
import com.siverhall.services.repos.EpisodeRepo;
import com.siverhall.services.repos.ShowRepo;

/**
 *  Guice module that binds all repositories to the current persistence unit specified in
 *  persistence.xml ("hibernate-manager"). Manual addition of each repository is mandatory.
 */
public class RepositoryModule extends JpaRepositoryModule {
    public RepositoryModule() {
        super("hibernate-manager");
    }

    @Override
    protected void bindRepositories(RepositoryBinder binder) {
        binder.bind(ShowRepo.class).to("hibernate-manager");
        binder.bind(EpisodeRepo.class).to("hibernate-manager");
    }
}
