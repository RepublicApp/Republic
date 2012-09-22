package com.republic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends Activity implements AdapterView.OnItemClickListener {
    private ArrayList<Class> activities = new ArrayList<Class>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ArrayList<String> homeList = new ArrayList<String>();
        homeList.add("User Profile");
        activities.add(ProfileActivity.class);
        homeList.add("Map");
        activities.add(MapActivity.class);
        homeList.add("Pub page example");
        activities.add(PubExampleActivity.class);
        homeList.add("Beer page example");
        activities.add(BeerExampleActivity.class);
        homeList.add("Camera");
        activities.add(CameraActivity.class);
        homeList.add("HTTP Stuff");
        activities.add(HTTPStuffActivity.class);
        homeList.add("Binnie Tests");
        activities.add(BinnieTestActivity.class);
        homeList.add("Chris Tests");
        activities.add(ChrisTestActivity.class);

        ListView homeListView = (ListView)findViewById(R.id.home_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, homeList);
        homeListView.setAdapter(adapter);
        homeListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(activities.size() > i){
            Intent intent = new Intent(this, activities.get(i));
            startActivity(intent);
        }
    }
}
