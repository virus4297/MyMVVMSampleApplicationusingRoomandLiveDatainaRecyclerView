package com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_DESCRIPTION";
    public static final String EXTRA_STATUS =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_STATUS";
    public static final String EXTRA_SNMP =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_SNMP";
    public static final String EXTRA_BANDWIDTH =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_BANDWIDTH";
    public static final String EXTRA_CPU =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_CPU";
    public static final String EXTRA_RAM =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_RAM";
    public static final String EXTRA_DISK =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_DISK";
    public static final String EXTRA_TIME =
            "com.example.mymvvmsampleapplicationusingroomandlivedatainarecyclerview.EXTRA_TIME";

    private TextView editTextTitle;
    private TextView editTextDescription;
    private TextView editTextStatus;
    private TextView editTextSnmp;
    private TextView editTextBandwidth;
    private TextView editTextCpu;
    private TextView editTextRam;
    private TextView editTextDisk;
    private TextView editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextStatus = findViewById(R.id.edit_text_status);
        editTextSnmp = findViewById(R.id.edit_text_snmp);
        editTextBandwidth = findViewById(R.id.edit_text_bandwidth);
        editTextCpu = findViewById(R.id.edit_text_cpu);
        editTextRam = findViewById(R.id.edit_text_ram);
        editTextDisk = findViewById(R.id.edit_text_disk);
        editTextTime = findViewById(R.id.edit_text_time);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextStatus.setText(intent.getStringExtra(EXTRA_STATUS));
            editTextSnmp.setText(intent.getStringExtra(EXTRA_SNMP));
            editTextBandwidth.setText(intent.getStringExtra(EXTRA_BANDWIDTH));
            editTextCpu.setText(intent.getStringExtra(EXTRA_CPU));
            editTextRam.setText(intent.getStringExtra(EXTRA_RAM));
            editTextDisk.setText(intent.getStringExtra(EXTRA_DISK));
            editTextTime.setText(intent.getStringExtra(EXTRA_TIME));
        } else {
            setTitle("Add Note");
        }
    }

/*    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String status = editTextStatus.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert a Title And Description", Toast.LENGTH_SHORT).show();
            return;
        }

        //ideally should edit db from here for insert/update

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_STATUS, status);

        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
}
