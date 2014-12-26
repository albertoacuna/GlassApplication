package com.capstone.glassapplication.DAL;

import android.util.Log;

import com.example.dascruel.myapplication.backend.models.userApi.UserApi;
import com.example.dascruel.myapplication.backend.models.userApi.model.CollectionResponseUser;
import com.example.dascruel.myapplication.backend.models.userApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;


/**
 * Created by DasCruel on 12/25/2014.
 */
public class DatastoreUserDAL implements UserDAL {
    @Override
    public User getUser(String name) {
        //TODO: get user from datastore
        UserApi.Builder userApiBuilder = new UserApi.Builder(
                AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);

        UserApi userApi = userApiBuilder.build();
        try {
            User user = userApi.get(name).execute();
            return user;
        }
        catch(IOException ex){
            Log.d("Could not get user",ex.getMessage());
        }

        return null;
    }

    @Override
    public User updateUser(String name, User user) {
        //TODO: update user to datastore

        UserApi.Builder userApiBuilder = new UserApi.Builder(
                AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);

        UserApi userApi = userApiBuilder.build();
        try {
            User userResponse = userApi.update(name, user).execute();
            return userResponse;
        }catch(IOException ex){
            Log.d("Could not update user",ex.getMessage());
        }

        return null;
    }
}
