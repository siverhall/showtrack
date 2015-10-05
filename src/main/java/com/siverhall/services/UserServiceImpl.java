package com.siverhall.services;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.siverhall.dataobjects.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserServiceImpl implements UserService {

    @Inject
    private EntityManager em;

    @Override
    @Transactional
    public User getUser(String username) {
        Query q = em.createQuery("FROM User");
        q.setMaxResults(1);
        return (User) q.getSingleResult();
    }
}
