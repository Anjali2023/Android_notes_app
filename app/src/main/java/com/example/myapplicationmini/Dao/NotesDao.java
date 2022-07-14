package com.example.myapplicationmini.Dao;

import androidx.lifecycle.LiveData;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplicationmini.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")//det data in livedata format repositary used
    LiveData<List<Notes>> getallNotes();


    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")//det data in livedata format repositary used
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")//det data in livedata format repositary used
    LiveData<List<Notes>> lowToHigh();

    @Insert
    void  insertNotes(Notes... notes);


    @Query("DELETE FROM Notes_Database WHERE id=:id ")
    void deleteNotes(int id);


    @Update
    void  updateNotes(Notes notes);


}
