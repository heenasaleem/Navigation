package com.example.heenasaleembaba.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Junaid on 5/3/2016.
 */
public class menu1_fragment extends Fragment {
    View myview;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myview=inflater.inflate(R.layout.menu1_layout,container,false);
        return myview;
    }
}
