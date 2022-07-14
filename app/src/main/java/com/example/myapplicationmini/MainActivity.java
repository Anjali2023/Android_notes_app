package com.example.myapplicationmini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplicationmini.Activity.InsertNotes;
import com.example.myapplicationmini.Adapter.NotesAdapter;
import com.example.myapplicationmini.Model.Notes;
import com.example.myapplicationmini.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    TextView nofilter, highfilter, lowfilter;
    List<Notes> FilterNotesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNotesBtn = findViewById(R.id.newNoteBtn);
        notesRecycler = findViewById(R.id.notesRecycler);

        notesViewModel=ViewModelProviders.of(this).get(NotesViewModel.class);

        nofilter = findViewById(R.id.nofilter);
        highfilter = findViewById(R.id.highfilter);
        lowfilter = findViewById(R.id.lowfilter);

        nofilter.setBackgroundResource(R.drawable.filter_sel_shape);

        nofilter.setOnClickListener(view -> {

            loadData(0);
            highfilter.setBackgroundResource(R.drawable.filter_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_sel_shape);
        });

        highfilter.setOnClickListener(view -> {
            loadData(1);
            highfilter.setBackgroundResource(R.drawable.filter_sel_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_shape);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });

        lowfilter.setOnClickListener(view -> {

            loadData(2);
            highfilter.setBackgroundResource(R.drawable.filter_shape);
            lowfilter.setBackgroundResource(R.drawable.filter_sel_shape);
            nofilter.setBackgroundResource(R.drawable.filter_shape);
        });

        newNotesBtn.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this,InsertNotes.class));

        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                FilterNotesList=notes;
            }
        });

    }
    private void loadData(int i){
        if(i==0){
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesList=notes;
                }
            });
        }else if(i==1){
            notesViewModel.highfilter.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesList=notes;
                }
            });
        }else if(i==2){
            notesViewModel.lowfilter.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    FilterNotesList=notes;
                }
            });
        }
    }

    public void setAdapter(List<Notes> notes){
        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        notesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate( R.menu.search,menu);

        MenuItem menuItem =menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes ");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String s) {

        ArrayList<Notes> FilterNames = new ArrayList<>();
        for(Notes notes:this.FilterNotesList)
        {
            if(notes.notesTitle.contains(s) || notes.notesSubtitle.contains(s) || notes.notes.contains(s))
            {
                FilterNames.add(notes);
            }
        }
        this.adapter.searchNotes(FilterNames);
    }
}



















