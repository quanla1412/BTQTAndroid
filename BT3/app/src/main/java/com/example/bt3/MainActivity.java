package com.example.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayList<String> urlList;
    private ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listDanhMuc);

        arrayList = new ArrayList<String>();
        arrayList.add("Proteins");
        arrayList.add("Amino Acids");
        arrayList.add("Grains and Starches");
        arrayList.add("Fibers and Legumes");
        arrayList.add("Vitamins");
        arrayList.add("Minerals");
        arrayList.add("Nutraceuticals");
        arrayList.add("Fats and Oils");
        arrayList.add("Preservatives");
        arrayList.add("Ingredients and substances to avoid");
        arrayList.add("Pet Food Ingredients");

        urlList = new ArrayList<String>();
        urlList.add("https://www.petfoodindustry.com/rss/topic/292-proteins");
        urlList.add("https://www.petfoodindustry.com/rss/topic/293-amino-acids");
        urlList.add("https://www.petfoodindustry.com/rss/topic/294-grains-and-starches");
        urlList.add("https://www.petfoodindustry.com/rss/topic/295-fibers-and-legumes");
        urlList.add("https://www.petfoodindustry.com/rss/topic/296-vitamins");
        urlList.add("https://www.petfoodindustry.com/rss/topic/297-minerals");
        urlList.add("https://www.petfoodindustry.com/rss/topic/298-nutraceuticals");
        urlList.add("https://www.petfoodindustry.com/rss/topic/300-fats-and-oils");
        urlList.add("https://www.petfoodindustry.com/rss/topic/301-preservatives");
        urlList.add("https://www.petfoodindustry.com/rss/topic/302-ingredients-and-substances-to-avoid");
        urlList.add("https://www.petfoodindustry.com/rss/topic/212-pet-food-ingredients");


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ListItemActivity.class);
                intent.putExtra("url", urlList.get(i));

                startActivity(intent);
            }
        });
    }
}