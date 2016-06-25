package com.example.heenasaleembaba.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Junaid on 5/3/2016.
 */
public class menu2_fragment extends Fragment {
    View rootview;
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview=inflater.inflate(R.layout.menu2_layout,container,false);
        return rootview;
    }
}
