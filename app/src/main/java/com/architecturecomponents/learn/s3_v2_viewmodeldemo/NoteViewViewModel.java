package com.architecturecomponents.learn.s3_v2_viewmodeldemo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

public class NoteViewViewModel extends AndroidViewModel {
    private String TAG=this.getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase noteDb;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewViewModel(Application application){
        super(application);
        noteDb=NoteRoomDatabase.getDatabase(application);
        noteDao=noteDb.noteDao();
        mAllNotes=noteDao.getAllNotes();
    }
     public  void  insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);

 }
    LiveData<List<Note>> getAllmNotes(){
        return  mAllNotes;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG,"View Model destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note,Void,Void> {
        NoteDao mNoteDao;
        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }


    }
}
