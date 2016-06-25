package com.example.heenasaleembaba.navigation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final List web;
    private final Integer[] imageId;
    public CustomAdapter(Activity context,
                         List web, Integer[] imageId) {
        super(context, R.layout.listcustom, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }




    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listcustom, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web.get(position).toString());
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}