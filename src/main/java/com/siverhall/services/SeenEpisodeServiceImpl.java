package com.siverhall.services;

import com.google.inject.Inject;

import javax.persistence.EntityManager;

public class SeenEpisodeServiceImpl implements SeenEpisodeService {

    @Inject
    private EntityManager em;

}
