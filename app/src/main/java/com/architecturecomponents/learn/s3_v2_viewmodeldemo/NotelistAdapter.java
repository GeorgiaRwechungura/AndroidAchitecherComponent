package com.architecturecomponents.learn.s3_v2_viewmodeldemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotelistAdapter extends RecyclerView.Adapter <NotelistAdapter.NoteViewHolder>{

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<Note>mNotes;
    //Constructor
    public NotelistAdapter(Context context){
      layoutInflater=LayoutInflater.from(context);
      mContext=context;

    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=layoutInflater.inflate(R.layout.list_item,parent,false);
        NoteViewHolder viewHolder=new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if(mNotes!=null){
        Note note=mNotes.get(position);
        holder.setData(note.getMNote(),position);
        }else {
            holder.noteItemView.setText(R.string.new_note);
        }

    }

    @Override
    public int getItemCount() {
        if(mNotes!=null){
            return mNotes.size();
        }else return 0;

    }
    public void setNotes(List<Note> notes){
        mNotes=notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView noteItemView;
        private  int mPosition;

        public NoteViewHolder(@NonNull View itemView) {

            super(itemView);
        }

        public void setData(String mNote, int position) {
            noteItemView.setText(mNote);

            mPosition=position;
        }
    }
}
