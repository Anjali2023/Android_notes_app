package com.example.myapplicationmini.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplicationmini.Model.Notes;
import com.example.myapplicationmini.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> highfilter;
    public LiveData<List<Notes>> lowfilter;


    public NotesViewModel( Application application) {
        super(application);

        repository=new NotesRepository(application);

        getAllNotes= repository.getallNotes;
        highfilter=repository.highfilter;
        lowfilter=repository.lowfilter;


    }

    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }


    public void deleteNote(int id){
        repository.deleteNotes(id);
    }


    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }



}





















