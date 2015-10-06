package com.siverhall.services;

import com.google.inject.Inject;

import javax.persistence.EntityManager;

public class EpisodeServiceImpl implements EpisodeService {

    @Inject
    private EntityManager em;

}
