package com.siverhall.services;

import com.google.inject.ImplementedBy;

/**
 *  Interface for methods connecting to remote API.
 */
@ImplementedBy(EpisodeApiServiceImpl.class)
public interface EpisodeApiService {
    boolean findShow(String name);
}
