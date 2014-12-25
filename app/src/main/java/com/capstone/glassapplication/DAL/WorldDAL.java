package com.capstone.glassapplication.DAL;

import com.example.dascruel.myapplication.backend.models.worldApi.model.World;

import java.util.List;

/**
 * Created by DasCruel on 12/25/2014.
 */
public interface WorldDAL {
    public World getWorld(String name);
    public List<String> getListOfWorlds();

}
