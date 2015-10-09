package com.siverhall.services;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Show;
import com.siverhall.services.repos.ShowRepo;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ShowServiceImpl implements ShowService {

    @Inject
    private ShowRepo showRepo;

    @Override
    public Show findById(Long id) {
        return showRepo.findById(id);
    }

    @Override
    public List<Show> getCurrentShows() {
        return showRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
