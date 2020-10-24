package com.architecturecomponents.learn.s3_v2_viewmodeldemo;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    private String TAG = this.getClass().getSimpleName();
    private NoteViewViewModel noteViewViewModel;
    private NotelistAdapter notelistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=findViewById(R.id.recycleView);
        notelistAdapter=new NotelistAdapter(this);
        recyclerView.setAdapter(notelistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        noteViewViewModel = ViewModelProviders.of(this).get(NoteViewViewModel.class);
        noteViewViewModel.getAllmNotes().observe((LifecycleOwner) this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {

              notelistAdapter.setNotes(notes);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            //code to insert note

            String note_id= UUID.randomUUID().toString();
            Note note= new Note(note_id,data.getStringExtra(NewNoteActivity.NOTE_ADDED));
             noteViewViewModel.insert(note);
            Toast.makeText(getApplicationContext(), R.string.save, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),R.string.not_saved,Toast.LENGTH_LONG).show();
        }
    }
}
