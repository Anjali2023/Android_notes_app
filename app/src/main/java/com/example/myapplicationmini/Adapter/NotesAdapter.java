package com.example.myapplicationmini.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationmini.Activity.UpdateNotesActivity;
import com.example.myapplicationmini.MainActivity;
import com.example.myapplicationmini.Model.Notes;
import com.example.myapplicationmini.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotes;
    //String[] nn = new String[3];


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {

        this.mainActivity = mainActivity;
        this.notes = notes;
       allNotes=new ArrayList<>(notes);

    }

    public void searchNotes(List<Notes> filterName){

        this.notes=filterName;
        notifyDataSetChanged();

    }

    @Override
    public notesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewholder holder, int position) {

        Notes note = notes.get(position);

        if (note.notesPriority.equals("1")) {
            holder.priority.setBackgroundResource(R.drawable.green_shape);
        } else if (note.notesPriority.equals("2")) {
            holder.priority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (note.notesPriority.equals("3")) {
            holder.priority.setBackgroundResource(R.drawable.red_shape);
        }
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);



        holder.itemView.setOnClickListener(v->{
            Intent intent=new Intent(mainActivity, UpdateNotesActivity.class);

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.notesSubtitle);
            intent.putExtra("note",note.notes);
            intent.putExtra("priority",note.notesPriority);

            mainActivity.startActivity(intent);

        });





        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nn[0]=note.notesTitle;
                //nn[1]=note.notesSubtitle;
                //nn[2]=note.notes;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, note.notes);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                mainActivity.startActivity(shareIntent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewholder extends RecyclerView.ViewHolder {

        TextView title, subtitle, date;
        View priority;
       Button share;

        public notesViewholder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            date = itemView.findViewById(R.id.notesDate);
            priority = itemView.findViewById(R.id.notesPriority);
            share=itemView.findViewById(R.id.share);
        }
    }

}
