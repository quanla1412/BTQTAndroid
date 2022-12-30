package com.example.bt2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> date;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context context, ArrayList<String> title, ArrayList<String> description, ArrayList<String> date){
        this.context = context;
        this.title = title;
        this.description = description;
        this.date = date;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list, null);
        TextView titleList = convertView.findViewById(R.id.listTitle);
        TextView descriptionList = convertView.findViewById(R.id.listDescription);
        TextView dateList = convertView.findViewById(R.id.listDate);

        titleList.setText(title.get(position));
        descriptionList.setText(description.get(position));
        dateList.setText(date.get(position));

        return convertView;
    }
}
