package com.capstone.glassapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.capstone.glassapplication.DAL.DatastoreWorldDAL;
import com.example.dascruel.myapplication.backend.models.worldApi.model.*;

import java.util.List;


public class ChooseWorldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_world);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_world, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListWorldsAsyncTask extends AsyncTask<Void, Void, List<String>>{

        @Override
        protected List<String> doInBackground(Void... voids) {
            DatastoreWorldDAL worldDal = new DatastoreWorldDAL();
            return worldDal.getListOfWorlds();
        }
    }

    private class GetWorldAsyncTask extends AsyncTask<String, Void, World>{

        @Override
        protected World doInBackground(String... strings) {
            DatastoreWorldDAL worldDal = new DatastoreWorldDAL();
            World world = worldDal.getWorld(strings[0]);

            if(world == null){
                //TODO: implement some error handling
            }

            return world;
        }

        @Override
        protected void onPostExecute(World result){
            //TODO: call the activity that actually starts the game. Chooseworld activity can be destroyed at this point

        }
    }
}
