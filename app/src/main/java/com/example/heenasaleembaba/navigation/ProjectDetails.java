package com.example.heenasaleembaba.navigation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faheem on 11-06-2016.
 */
public class ProjectDetails extends Fragment {
    View myview;
    ListView listView;
    public String TAG;
//    ArrayAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TAG = "STATUS";

        myview = inflater.inflate(R.layout.project_details, container, false);
        listView = (ListView) myview.findViewById(R.id.listProject_id);

      /*  String[] title = {"Write some software", "build a website", "build Android app","Small Customization on ASP.NET Page",};
        String[] type = {"PHP,.NET,MySQL", ".NET,JAVA", "JAVA", "ASP.NET,C++",
                "Engineering & Science"};
        String[] amt = {"$50 USD", "$20 USD", "$10 USD", "$30 USD",};
        String[] bids = {"12","4","4","8"};*/

        // adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, title);
        //  ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list)

        // CustomAdapter2 adapter = new CustomAdapter2(getActivity(),title,type,amt,bids);
        // listView.setAdapter(adapter);


        if (MainActivity.fragValue.equals("Website")) {
        }
        getData();
        return myview;
    }


    public void getData() {
        class GetDataJSON extends AsyncTask<String, Void, List> {
            ArrayList title, type, amount, bids;

            @Override
            protected List doInBackground(String... params) {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("category",params[0]));
                title = new ArrayList();
                type = new ArrayList();
                amount = new ArrayList();
                bids = new ArrayList();
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://freelancing.pe.hu/SendFromServer2.php");
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                } catch (UnsupportedEncodingException e) {
                    Log.e("Exception", e.toString());
                }
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

                        String [] items = json_data.getString("item").split("#");

                        title.add(items[0]);
                        type.add(items[1]);
                        amount.add(items[2]);
                        bids.add(items[3]);


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
                Log.e("LIST RETURNED - ", amount.toString());
                return title;
            }

           @Override
            protected void onPostExecute(List list) {

                //ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);

               CustomAdapter2 adapter = new CustomAdapter2(getActivity(),title, type, amount, bids);
                listView.setAdapter(adapter);
                // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                // @Override
                // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(getActivity(),ProjectDetails.class));
                // FragmentManager fragmentmanager= getFragmentManager();
                MainActivity.fragValue = "website";
                // fragmentmanager.beginTransaction().replace(R.id.content_frame,new ProjectDetails()).commit();

                // }
                //});
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute("ECommerce");
//    }

    }

}
