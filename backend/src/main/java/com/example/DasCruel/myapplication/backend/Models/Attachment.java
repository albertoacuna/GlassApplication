package com.example.DasCruel.myapplication.backend.Models;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;

/**
 * Created by DasCruel on 12/25/2014.
 */

public @Data class Attachment {
    private  String Text;
    private AttachmentType Type;
    private Blob Blob;


    private enum AttachmentType{
        Image,
        Video,
        Text,
        Audio
    }
}
