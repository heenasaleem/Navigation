package com.example.heenasaleembaba.navigation;

/**
 * Created by HEENA SALEEM BABA on 24-06-2016.
 */

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter3 extends ArrayAdapter<String> {

    private final Activity context;

    private final Integer[] imageId;
    private final List name;
    private final List skills;
    private final List country;

    public CustomAdapter3(Activity context, Integer[] imageId, List name, List skills, List country) {
        super(context, R.layout.listcustom, name);
        this.context = context;
        this.name = name;
        this.imageId = imageId;
        this.skills = skills;
        this.country = country;

    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Log.e("inside getView","getView working");

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listcustom3, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_id);
        TextView txtName = (TextView) rowView.findViewById(R.id.name_id);
        TextView txtSkills = (TextView) rowView.findViewById(R.id.skills_id);
        TextView txtCountry = (TextView) rowView.findViewById(R.id.country_id);


        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            /*Log.e("Title 1 ",title[position]);
            txtTitle.setText(title[position]);
            txtTitle2.setText(type[position]);
            txtTitle3.setText(amt[position]);
            txtTitle4.setText(bids[position]);*/
        imageView.setImageResource(imageId[position]);
        txtName.setText(name.get(position).toString());
        txtSkills.setText(skills.get(position).toString());
        txtCountry.setText(country.get(position).toString());
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

