package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.Show;

@ImplementedBy(ShowServiceImpl.class)
public interface ShowService {

    Show findShow(String name);
}
