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

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        editTextTitle = (EditText) findViewById(R.id.editTextTitleNote);
        editTextDescription = (EditText) findViewById(R.id.editTextTextMultiLine);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
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
        if (id == R.id.saveNewNote) {
            NoteHelper noteHelper = new NoteHelper(this, "Note.sqlite", null, 1);

            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String now = formatter.format(date);

            if(title.length() == 0 && description.length() == 0){
                Toast.makeText(this, "Can't save", Toast.LENGTH_SHORT).show();
                return true;
            }

            noteHelper.QueryData("INSERT INTO Note(title, description, date) VALUES(\"" + title + "\", \"" + description + "\", \"" + now + "\")");
            Toast.makeText(this, "Save successfully!", Toast.LENGTH_SHORT).show();

            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}