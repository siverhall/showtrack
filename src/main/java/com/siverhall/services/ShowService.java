package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.Show;

import java.util.List;

/**
 *  Interface for db handling of Show objects.
 */
@ImplementedBy(ShowServiceImpl.class)
public interface ShowService {

    Show findById(Long id);

    List<Show> getCurrentShows();
}
