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
public class User {
    @Id @Getter @Setter private long id;
    @Getter @Setter private String Name;
    @Getter @Setter private List<World> SavedWorlds;
}
