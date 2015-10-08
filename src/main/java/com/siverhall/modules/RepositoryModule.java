package com.siverhall.modules;

import com.google.code.guice.repository.configuration.JpaRepositoryModule;
import com.google.code.guice.repository.configuration.RepositoryBinder;
import com.siverhall.services.repos.ShowRepo;

/**
 *  Guice module that binds all repositories to the current persistence unit specified in
 *  persistence.xml ("hibernate-manager")
 */
public class RepositoryModule extends JpaRepositoryModule {
    @Override
    protected void bindRepositories(RepositoryBinder binder) {
        binder.bind(ShowRepo.class).to("hibernate-manager");
    }
}
