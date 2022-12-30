package com.example.bt3;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListItemActivity extends AppCompatActivity {

    private static String URL;

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private List<Item> listItem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = (ListView) findViewById(R.id.listViewItem);


        arrayList = new ArrayList<String>();

        URL = getIntent().getExtras().getString("url");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String result = response.body().string();

                    ListItemActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XmlPullParserHandler parser = new XmlPullParserHandler();

                                List<Item> items = parser.parse(result);
                                listItem = items;

                                for ( Item item :
                                    items) {
                                    arrayList.add(item.getTitle());
                                }

                                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                                listView.setAdapter(adapter);
                            }
                            catch (Exception e){
                                e.printStackTrace();

                            }

                        }
                    });
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDialog(i);
            }
        });


    }

    private void openDialog(int i){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);

        Window window = dialog.getWindow();
        if(window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        Button btnMore = (Button) dialog.findViewById(R.id.btnMore);
        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        TextView dialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
        TextView dialogDescription = (TextView) dialog.findViewById(R.id.dialogDescription);
        TextView dialogName = (TextView) dialog.findViewById(R.id.dialogName);
        TextView dialogDate = (TextView) dialog.findViewById(R.id.dialogDate);
        ImageView dialogImage = (ImageView) dialog.findViewById(R.id.dialogImage);

        Item item = listItem.get(i);

        dialogTitle.setText(item.getTitle());
        dialogDescription.setText(item.getDescription());
        dialogName.setText(item.getImgName());
        dialogDate.setText(item.getDate());
        Picasso.get().load(item.getImgURL()).into(dialogImage);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(item.getLink()));
                startActivity(i);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
