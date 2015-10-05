package com.siverhall.services;

import com.google.inject.ImplementedBy;
import com.siverhall.dataobjects.User;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    public User getUser(String username);
}
