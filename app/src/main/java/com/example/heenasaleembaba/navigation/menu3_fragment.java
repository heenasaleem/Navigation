package com.example.heenasaleembaba.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class menu3_fragment extends Fragment {
    View rootview;
    // ArrayAdapter adapter;
    private View view;
    ListView listView;
    public String TAG;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TAG = "STATUS";
        rootview = inflater.inflate(R.layout.menu3_layout, container, false);

        listView = (ListView) rootview.findViewById(R.id.listValues);
        Log.e(TAG, "onCreateView: ");

       /* String[] values = {"Websites,IT & Software", "Writing & Content", "Design,Media & Architecture","Data Entry & Admin",
                "Engineering & Science"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, values);



        list.setAdapter(adapter);*/

        getData();
        return rootview;
    }


    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, List> {
            @Override
            protected List doInBackground(String... params) {
                ArrayList list = new ArrayList();

                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://freelancing.pe.hu/SendFromServer.php");
                // Depends on your web service
                //httppost.setHeader("Content-type", "application/json");
                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    Log.e(TAG, "entity" + entity.toString());
                    //inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    // json is UTF-8 by default
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    result = sb.toString();
                    Log.e(TAG, "result" + result);
                    JSONObject json_data = new JSONObject();
                    JSONArray jsonArray = new JSONArray(result);
                    int a = jsonArray.length();
                    Log.e("a = ", ((Integer) a).toString());

                    for (int i = 0; i < a; i++) {
                        json_data = jsonArray.getJSONObject(i);
                        list.add(json_data.getString("item"));

                        //imageURL.add(json_data.getString("img"));

                    }



                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: " + e.toString());
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                    } catch (Exception e) {
                        Log.e(TAG, "ISTREAM: " + e.toString());
                    }
                }
                return list;
            }

            @Override
            protected void onPostExecute(List list) {
                Integer[] imageId = {
                        R.drawable.ic_add,
                        R.drawable.ic_autorenew_black_24dp,
                        R.drawable.ic_face_black_24dp,
                        R.drawable.ic_menu_camera,
                        R.drawable.ic_menu_gallery,
                        R.drawable.ic_add1,
                        R.drawable.ic_android_black,
                        R.drawable.ic_autorenew,
                        R.drawable.ic_build_black

                };
                //ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
                CustomAdapter adapter = new CustomAdapter(getActivity(),list,imageId);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //startActivity(new Intent(getActivity(),ProjectDetails.class));
                        FragmentManager fragmentmanager= getFragmentManager();
                        MainActivity.fragValue = listView.getItemAtPosition(position).toString();
                        Log.e("Value", listView.getItemAtPosition(position).toString());
                        Bundle bundle=new Bundle();
                        bundle.putString("item", listView.getItemAtPosition(position).toString());
                        //set Fragmentclass Arguments
                        ProjectDetails fragobj = new ProjectDetails();
                        fragobj.setArguments(bundle);
                        fragmentmanager.beginTransaction().replace(R.id.content_frame,new ProjectDetails()).commit();



                    }
                });
            }
        }
         GetDataJSON g = new GetDataJSON();
        g.execute();
//    }

    }
}
