package com.capstone.glassapplication.DAL;

import android.util.Log;

import com.example.dascruel.myapplication.backend.models.worldApi.WorldApi;
import com.example.dascruel.myapplication.backend.models.worldApi.model.CollectionResponseWorld;
import com.example.dascruel.myapplication.backend.models.worldApi.model.World;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DasCruel on 12/25/2014.
 */
public class DatastoreWorldDAL implements WorldDAL {
    @Override
    public World getWorld(String name) {
        WorldApi.Builder builder = new WorldApi.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);

        builder.setRootUrl("http://localhost:8080/_ah/api");
        WorldApi client = builder.build();
        try {
            World worldResponse = client.get(name).execute();
            return worldResponse;
        }catch(IOException ex){
            Log.d("Could not get Worlds", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<String> getListOfWorlds() {
        WorldApi.Builder builder = new WorldApi.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
        List<String> worldNames = new ArrayList<String>();
        builder.setRootUrl("http://localhost:8080/_ah/api");
        WorldApi client = builder.build();
        try {
             CollectionResponseWorld worldCollection = client.list().execute();
            for(World world : worldCollection.getItems()){
                worldNames.add(world.getName());
            }
            return worldNames;

        }catch(IOException ex){
            Log.d("Could not get Worlds", ex.getMessage());
        }
        return null;
    }
}
