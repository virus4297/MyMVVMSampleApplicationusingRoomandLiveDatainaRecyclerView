package com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //copy from this

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(NoteViewModel.class);//new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update recyclerView
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            private HomeFragment homeFragment;

            @Override
            public void onItemClick(Note note) {
                //noteViewModel=ViewModelProviders.of(homeFragment).get(NoteViewModel.class);
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(AddEditNoteActivity.EXTRA_STATUS, note.getStatus());
                intent.putExtra(AddEditNoteActivity.EXTRA_SNMP, note.getSnmp());
                intent.putExtra(AddEditNoteActivity.EXTRA_BANDWIDTH,note.getBandwidth());
                intent.putExtra(AddEditNoteActivity.EXTRA_DISK,note.getDisk());
                intent.putExtra(AddEditNoteActivity.EXTRA_RAM,note.getRam());
                intent.putExtra(AddEditNoteActivity.EXTRA_CPU,note.getCpu());
                intent.putExtra(AddEditNoteActivity.EXTRA_TIME,note.getTime());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            String status = data.getStringExtra(AddEditNoteActivity.EXTRA_STATUS);
            String snmp = data.getStringExtra(AddEditNoteActivity.EXTRA_SNMP);
            long bandwidth = data.getLongExtra(AddEditNoteActivity.EXTRA_BANDWIDTH,1);
            long disk = data.getLongExtra(AddEditNoteActivity.EXTRA_DISK,1);
            long ram = data.getLongExtra(AddEditNoteActivity.EXTRA_RAM,1);
            long cpu = data.getLongExtra(AddEditNoteActivity.EXTRA_CPU,1);
            String time = data.getStringExtra(AddEditNoteActivity.EXTRA_TIME);

            Note note = new Note(title, description, status,snmp,bandwidth,cpu,ram,disk,time);
            noteViewModel.insert(note);

            Toast.makeText(this, "Note Saved.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id=data.getIntExtra(AddEditNoteActivity.EXTRA_ID,-1);
            if (id==-1){
                Toast.makeText(this, "Note Cannot be Updated!!", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            String status = data.getStringExtra(AddEditNoteActivity.EXTRA_STATUS);
            String snmp = data.getStringExtra(AddEditNoteActivity.EXTRA_SNMP);
            long bandwidth = data.getLongExtra(AddEditNoteActivity.EXTRA_BANDWIDTH,1);
            long disk = data.getLongExtra(AddEditNoteActivity.EXTRA_DISK,1);
            long ram = data.getLongExtra(AddEditNoteActivity.EXTRA_RAM,1);
            long cpu = data.getLongExtra(AddEditNoteActivity.EXTRA_CPU,1);
            String time = data.getStringExtra(AddEditNoteActivity.EXTRA_TIME);

            Note note=new Note(title, description, status,snmp,bandwidth,cpu,ram,disk,time);
            note.setId(id);

            noteViewModel.update(note);
            Toast.makeText(this, "Note Updated.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Note NOT Saved!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                noteViewModel.deleteAll();
                Toast.makeText(this, "All Notes Deleted!!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
