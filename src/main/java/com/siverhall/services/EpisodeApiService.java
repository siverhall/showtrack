package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.Show;

@ImplementedBy(EpisodeApiServiceImpl.class)
public interface EpisodeApiService {
    Show findShow(String name);
}
