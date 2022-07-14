package com.example.myapplicationmini.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationmini.Model.Notes;
import com.example.myapplicationmini.R;
import com.example.myapplicationmini.ViewModel.NotesViewModel;
import com.example.myapplicationmini.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    String priority = "1";
    String stitle, ssubtitle, snotes,spriority;
    int iid;
    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid=getIntent().getIntExtra("id",0);
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        snotes=getIntent().getStringExtra("note");
        spriority=getIntent().getStringExtra("priority");

        binding.upnotesTitle.setText(stitle);
        binding.upnoteSubtitle.setText(ssubtitle);
        binding.upnotesData.setText(snotes);

        notesViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotesViewModel.class);


        String uri = "@drawable/ic_baseline_done_24";
        int imageResourse = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResourse);

        if(spriority.equals("1"))
        {
            binding.greenPriority.setImageDrawable(res);
        }else if(spriority.equals("2"))
        {
            binding.yellowPriority.setImageDrawable(res);
        }else if(spriority.equals("3"))
        {
            binding.redPriority.setImageDrawable(res);
        }





        binding.greenPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageDrawable(res);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority = "1";
        });

        binding.yellowPriority.setOnClickListener(v -> {

            binding.yellowPriority.setImageDrawable(res);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority = "2";
        });

        binding.redPriority.setOnClickListener(v -> {

            binding.redPriority.setImageDrawable(res);
            binding.yellowPriority.setImageResource(0);
            binding.greenPriority.setImageResource(0);
            priority = "3";
        });

        binding.upNoteBtn.setOnClickListener( v -> {

            String title=binding.upnotesTitle.getText().toString();
            String subtitle=binding.upnoteSubtitle.getText().toString();
            String notes=binding.upnotesData.getText().toString();

            UpdateNotes(title,subtitle,notes);
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updateNotes= new Notes();

        updateNotes.id=iid;
        updateNotes.notesTitle=title;
        updateNotes.notesSubtitle=subtitle;
        updateNotes.notes=notes;
        updateNotes.notesDate=sequence.toString();
        updateNotes.notesPriority=priority;

        notesViewModel.updateNote(updateNotes);
        Toast.makeText(this,"Notes Updated Successfully",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_meny,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.ic_delete)
        {
            BottomSheetDialog sheetDialog= new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view= LayoutInflater.from(UpdateNotesActivity.this).
                    inflate(R.layout.delete_layout,(LinearLayout) findViewById(R.id.deletelayout));

            sheetDialog.setContentView(view);

            TextView yes,no;

            no= view.findViewById(R.id.nodel);
            yes=view.findViewById(R.id.yesdel);



            yes.setOnClickListener(v -> {

                notesViewModel.deleteNote(iid);
                finish();
            });

            no.setOnClickListener(v -> {

                sheetDialog.dismiss();
            });


            sheetDialog.show();

        }
        return true;
    }
}