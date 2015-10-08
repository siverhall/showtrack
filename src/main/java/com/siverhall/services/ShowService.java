package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.Show;

import java.util.List;

@ImplementedBy(ShowServiceImpl.class)
public interface ShowService {

    Show findShow(String name);

    List<Show> getCurrentShows();
}
