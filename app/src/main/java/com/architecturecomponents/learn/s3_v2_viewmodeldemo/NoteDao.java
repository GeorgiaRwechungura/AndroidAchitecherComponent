package com.architecturecomponents.learn.s3_v2_viewmodeldemo;

import android.arch.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NoteDao {


    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes")
     LiveData <List<Note>>getAllNotes();
}
