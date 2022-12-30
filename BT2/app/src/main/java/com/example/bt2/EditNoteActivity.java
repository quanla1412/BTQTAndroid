package com.example.bt2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private String idNote;
    private String titleNote;
    private String descriptionNote;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        editTextTitle = (EditText) findViewById(R.id.editTextTitleNote);
        editTextDescription = (EditText) findViewById(R.id.editTextTextMultiLine);
        idNote = getIntent().getExtras().getString("id");
        titleNote = getIntent().getExtras().getString("title");
        descriptionNote = getIntent().getExtras().getString("description");

        editTextTitle.setText(titleNote);
        editTextDescription.setText(descriptionNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_node, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.saveModifedNote) {
            NoteHelper noteHelper = new NoteHelper(this, "Note.sqlite", null, 1);

            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            if(title.length() == 0 && description.length() == 0){
                Toast.makeText(this, "Can't save", Toast.LENGTH_SHORT).show();
                return true;
            }

            noteHelper.QueryData("UPDATE Note SET title=\"" + title + "\", description =\"" + description + "\" WHERE id = " + idNote);
            Toast.makeText(this, "Save successfully!", Toast.LENGTH_SHORT).show();

            this.finish();
            return true;
        } else if (id == R.id.deleteNote) {
            NoteHelper noteHelper = new NoteHelper(this, "Note.sqlite", null, 1);

            noteHelper.QueryData("DELETE FROM NOTE WHERE id = " + idNote);
            Toast.makeText(this, "Delete successfully!", Toast.LENGTH_SHORT).show();

            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}