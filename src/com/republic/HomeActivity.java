package com.republic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends Activity {
    private ArrayList<Class> activities = new ArrayList<Class>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ArrayList<String> homeList = new ArrayList<String>();
        homeList.add("User Profile");
        activities.add(ProfileActivity.class);
        homeList.add("Map");
        homeList.add("Pub page example");
        homeList.add("Beer page example");
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
        homeListView.setOnItemClickListener(new HomeListListener(this));
    }

    private class HomeListListener implements AdapterView.OnItemClickListener{
        HomeActivity parent;

        public HomeListListener(HomeActivity parent){
            this.parent = parent;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(activities.size() > i){
                Intent intent = new Intent(parent, activities.get(i));
                startActivity(intent);
            }
        }
    }
}
