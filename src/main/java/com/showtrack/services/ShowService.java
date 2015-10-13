package com.showtrack.services;

import com.google.inject.ImplementedBy;
import com.showtrack.dataobjects.Show;

import java.util.List;

/**
 *  Interface for db handling of Show objects.
 */
public interface ShowService {

    Show findById(Long id);

    List<Show> getCurrentShows();
}
