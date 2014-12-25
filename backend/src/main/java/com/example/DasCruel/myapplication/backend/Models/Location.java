package com.example.DasCruel.myapplication.backend.Models;

import com.google.appengine.api.datastore.GeoPt;

import lombok.Data;

/**
 * Created by DasCruel on 12/24/2014.
 */
public @Data class Location {
    private String Name;
    private String Description;
    private boolean Visited;
    private GeoPt GeoLocation;
    private Attachment Attachment;
}
