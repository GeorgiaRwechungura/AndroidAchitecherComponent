package com.architecturecomponents.learn.s3_v2_viewmodeldemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey
    @NonNull
    private  String id;

     @NonNull
     @ColumnInfo(name = "note")
    private String mNote;

    @NonNull
    public String getId() {
        return id;
    }



    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getMNote() {
        return mNote;
    }
    public void setMNote(@NonNull String mNote) {
        this.mNote = mNote;
    }



    public Note(String id, String note){
         this.id=id;
         this.mNote=note;
     }
}

