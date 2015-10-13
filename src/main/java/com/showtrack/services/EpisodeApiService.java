package com.showtrack.services;

import com.google.inject.ImplementedBy;

/**
 *  Interface for methods connecting to remote API.
 */
public interface EpisodeApiService {
    boolean findShow(String name);
}
