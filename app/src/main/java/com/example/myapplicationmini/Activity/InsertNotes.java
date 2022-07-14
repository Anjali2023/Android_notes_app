package com.example.myapplicationmini.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationmini.Model.Notes;
import com.example.myapplicationmini.ViewModel.NotesViewModel;
import com.example.myapplicationmini.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotes extends AppCompatActivity{

    String title, subtitle, notes;
    ActivityInsertNotesBinding binding;

    NotesViewModel notesViewModel;
    String priority ="1";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String uri ="@drawable/ic_baseline_done_24";
        int imageResourse = getResources().getIdentifier(uri,null,getPackageName());
        Drawable res = getResources().getDrawable(imageResourse);

        notesViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotesViewModel.class);


        binding.greenPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageDrawable(res);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority="1";
        });

        binding.yellowPriority.setOnClickListener(v -> {

            binding.yellowPriority.setImageDrawable(res);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority="2";
        });

        binding.redPriority.setOnClickListener(v -> {

            binding.redPriority.setImageDrawable(res);
            binding.yellowPriority.setImageResource(0);
            binding.greenPriority.setImageResource(0);
            priority="3";
        });

        binding.doneNoteBtn.setOnClickListener(v ->{
            title=binding.notesTitle.getText().toString();
            subtitle=binding.noteSubtitle.getText().toString();
            notes=binding.notesData.getText().toString();

            CreateNotes(title,subtitle,notes);
        });


    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes notes1 = new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notes=notes;
        notes1.notesDate=sequence.toString();
        notes1.notesPriority=priority;

        notesViewModel.insertNote(notes1);
        Toast.makeText(this,"Notes Created Successfully",Toast.LENGTH_SHORT).show();
        finish();
    }
}