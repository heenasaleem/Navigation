package com.example.heenasaleembaba.navigation;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Faheem on 12-06-2016.
 */

    public class CustomAdapter2 extends ArrayAdapter<String> {

    private final Activity context;
    private final List title;
    private final List type;
    private final List amt;
    private final List bids;
   // private final String[]  title;
    //  private final Integer[] imageId;
   // private final String[]  type;
   // private final String[]  amt;
   // private final String[]  bids;




        public CustomAdapter2(Activity context, List title, List type, List amt, List bids) {
            super(context, R.layout.listcustom2, title);
            this.context = context;

            this.title = title;
            this.type = type;
            this.amt = amt;
            this.bids = bids;
          //  Log.e("Title 1 ",title[0]);


        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {

            Log.e("inside getView","getView working");

            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listcustom2, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt1);
            TextView txtTitle2 = (TextView) rowView.findViewById(R.id.txt2);
            TextView txtTitle3 = (TextView) rowView.findViewById(R.id.txt3);
            TextView txtTitle4= (TextView) rowView.findViewById(R.id.txt4);

            //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            /*Log.e("Title 1 ",title[position]);
            txtTitle.setText(title[position]);
            txtTitle2.setText(type[position]);
            txtTitle3.setText(amt[position]);
            txtTitle4.setText(bids[position]);*/
            txtTitle.setText(title.get(position).toString());
            txtTitle2.setText(type.get(position).toString());
            txtTitle3.setText(amt.get(position).toString());
            txtTitle4.setText(bids.get(position).toString());
            //imageView.setImageResource(imageId[position]);
            return rowView;
        }
    }

