package com.example.DasCruel.myapplication.backend.Models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by DasCruel on 12/24/2014.
 */

@Entity
public class World {
    @Id @Getter @Setter private long Id;
    @Getter @Setter private String Name;
    @Getter @Setter private String Description;
    @Getter @Setter private List<Location> Locations;
}
