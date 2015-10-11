package com.showtrack.services;

import com.google.inject.Inject;
import com.showtrack.dataobjects.Show;
import com.showtrack.services.repos.ShowRepo;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 *  Implementation of the ShowService interface
 */
public class ShowServiceImpl implements ShowService {

    @Inject
    private ShowRepo showRepo;

    /**
     *  Find a show based on internal id in the database
     */
    @Override
    public Show findById(Long id) {
        return showRepo.findById(id);
    }

    /**
     *  Returns all shows currently existing in the database, sorted by name in ascending order.
     */
    @Override
    public List<Show> getCurrentShows() {
        return showRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
