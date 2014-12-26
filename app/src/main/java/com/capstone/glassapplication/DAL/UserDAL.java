package com.capstone.glassapplication.DAL;

import com.example.dascruel.myapplication.backend.models.userApi.model.User;

import java.util.List;

/**
 * Created by DasCruel on 12/25/2014.
 */
public interface UserDAL {
    public User getUser(String name);
    public User updateUser(String name, User user);
}
