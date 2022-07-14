package com.example.myapplicationmini.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplicationmini.Dao.NotesDao;
import com.example.myapplicationmini.Database.NotesDatabase;
import com.example.myapplicationmini.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;//repositary to view model
    public LiveData<List<Notes>> getallNotes;

    public LiveData<List<Notes>> highfilter;
    public LiveData<List<Notes>> lowfilter;


    public NotesRepository(Application application) {

        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();

        highfilter=notesDao.highToLow();
        lowfilter=notesDao.lowToHigh();
    }


    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

   public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }


}
