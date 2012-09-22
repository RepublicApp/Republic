package com.republic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends Activity {
    private ArrayList<Class> activities = new ArrayList<Class>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ArrayList<String> homeList = new ArrayList<String>();
        homeList.add("Map");
        homeList.add("Profile");
        homeList.add("Camera");
        homeList.add("HTTP Stuff");
        homeList.add("Binnie Tests");
        homeList.add("Chris Tests");

        ListView homeListView = (ListView)findViewById(R.id.home_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, homeList);
        homeListView.setAdapter(adapter);
        homeListView.setOnItemClickListener(new HomeListListener());
    }

    private class HomeListListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //Intent intent = new Intent(this, ProfileActivity.class);     // Make a new intent with Display activity
            //startActivity(intent);
        }
    }
}
