package com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> basicNotes;
    private LiveData<List<Note>> allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository=new NoteRepository(application);
        allNotes=repository.getAllNotes();
     }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAll(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getBasicNotes(){
        return basicNotes;
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
