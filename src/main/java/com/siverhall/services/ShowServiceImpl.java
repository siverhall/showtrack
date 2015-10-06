package com.siverhall.services;

import com.google.inject.Inject;
import com.siverhall.dataobjects.Show;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ShowServiceImpl implements ShowService {

    @Inject
    private EntityManager em;

    @Override
    public Show findShow(String name) {
        Query q = em.createQuery("SELECT s FROM Show s WHERE s.name LIKE :name");
        q.setParameter("name", name);
        return (Show) q.getSingleResult();
    }
}
