package com.example.bt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> idList;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteHelper noteHelper = new NoteHelper(this, "Note.sqlite", null, 1);

        noteHelper.QueryData("CREATE TABLE IF NOT EXISTS Note(Id INTEGER PRIMARY KEY AUTOINCREMENT, title NVARCHAR(50), description NVARCHAR(200), date DATETIME)");

        Cursor cursor = noteHelper.GetData("SELECT * FROM Note");

        idList = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();
        while (cursor.moveToNext()){
            idList.add(cursor.getString(0));
            title.add(cursor.getString(1));
            description.add(cursor.getString(2));
            date.add(cursor.getString(3));
        }

        listView = (ListView) findViewById(R.id.listNotes);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), title, description, date);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
                intent.putExtra("id", idList.get(position));
                intent.putExtra("title", title.get(position));
                intent.putExtra("description", description.get(position));
                startActivity(intent);
            }
        });
        listView.setAdapter(customBaseAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addNewItem) {
            Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();NoteHelper noteHelper = new NoteHelper(this, "Note.sqlite", null, 1);

        noteHelper.QueryData("CREATE TABLE IF NOT EXISTS Note(Id INTEGER PRIMARY KEY AUTOINCREMENT, title NVARCHAR(50), description NVARCHAR(200), date DATETIME)");

        Cursor cursor = noteHelper.GetData("SELECT * FROM Note");

        idList = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();
        while (cursor.moveToNext()){
            idList.add(cursor.getString(0));
            title.add(cursor.getString(1));
            description.add(cursor.getString(2));
            date.add(cursor.getString(3));
        }

        listView = (ListView) findViewById(R.id.listNotes);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), title, description, date);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
                intent.putExtra("id", idList.get(position));
                intent.putExtra("title", title.get(position));
                intent.putExtra("description", description.get(position));
                startActivity(intent);
            }
        });
        listView.setAdapter(customBaseAdapter);
    }
}