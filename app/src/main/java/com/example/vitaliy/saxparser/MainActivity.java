package com.example.vitaliy.saxparser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.example.vitaliy.saxparser.R.id.lvPcsPost;

public class MainActivity extends AppCompatActivity {

    ListView lvPlaceMarkers;
    ArrayList<PlaceMarkValue> placeMarkValueArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvPlaceMarkers = (ListView) findViewById(R.id.lvPcsPost);
        lvPlaceMarkers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (placeMarkValueArrayList != null && placeMarkValueArrayList.size() > 0) {
                    Intent intentShowPost = new Intent(Intent.ACTION_VIEW, Uri.parse(placeMarkValueArrayList.get(position).getDescriptrion()));
                    startActivity(intentShowPost);
                }
            }
        });
        new PlaceMarkAsync().execute();
    }

    class PlaceMarkAsync extends AsyncTask<Void, Void, Void>{

        ProgressDialog pd;
        XMLHelper helper;


        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "PCSalt", "Loading posts for PCSalt.com ...", true, false);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            helper = new XMLHelper();
            helper.get();
            placeMarkValueArrayList = helper.getPlaceMarkValues();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            PlaceMarkBaseAdapter placeMarkBaseAdapter = new PlaceMarkBaseAdapter(MainActivity.this, placeMarkValueArrayList);
            lvPlaceMarkers.setAdapter(placeMarkBaseAdapter);
            pd.dismiss();
        }

    }
}
