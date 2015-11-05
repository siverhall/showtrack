package com.showtrack.services;

import com.google.inject.ImplementedBy;
import com.showtrack.dataobjects.ShowWrapper;

import java.util.List;

/**
 *  Interface for methods connecting to remote API.
 */
public interface EpisodeApiService {
    List<ShowWrapper> findShow(String name);

}
