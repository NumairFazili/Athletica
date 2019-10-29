package com.example.athletica.ui.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.athletica.R;
import com.example.athletica.data.search.SearchManager;
import com.example.athletica.ui.event.ViewEventActivity;
import com.example.athletica.ui.facility.ViewFacilityActivity;
import com.example.athletica.ui.profile.ViewProfileActivity;

import java.util.ArrayList;


/*
This boundary class displays the search results corresponding to each entity (users/facilities/events)
 */


public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {


    private String query;
    private com.example.athletica.data.search.SearchManager searchManager;
    private ListView lvFacility, lvEvents, lvUsers;
    private Button btnViewAllFacility, btnVidewAllEvent, btnViewAllUser;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        lvFacility = findViewById(R.id.lv_facility);
        lvEvents = findViewById(R.id.lv_event);
        lvUsers = findViewById(R.id.lv_user);
        btnViewAllFacility = findViewById(R.id.action_view_all_facility);
        btnVidewAllEvent = findViewById(R.id.action_view_all_event);
        btnViewAllUser = findViewById(R.id.action_view_all_user);

        btnViewAllFacility.setOnClickListener(this);
        btnViewAllUser.setOnClickListener(this);
        btnVidewAllEvent.setOnClickListener(this);

        // Get the search query from intent.
        intent = getIntent();
        query = intent.getStringExtra("query");
        searchManager = new SearchManager(this, query);


        searchManager.getFacilities(this);
        searchManager.getEvents(this);
        searchManager.getUsers(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    // this is used to initialize the  recycler view


    public void initListView(ArrayList<String> names, final ArrayList<String> index, int id) {
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);


        if (id == 0) {
            lvFacility.setAdapter(arrayAdapter);
            lvFacility.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    intent = new Intent(SearchResultActivity.this, ViewFacilityActivity.class);
                    intent.putExtra("index", index.get(i));
                    startActivity(intent);
                }
            });
        } else if (id == 1) {
            lvEvents.setAdapter(arrayAdapter);
            lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    intent = new Intent(SearchResultActivity.this, ViewEventActivity.class);
                    intent.putExtra("key", index.get(i));
                    startActivity(intent);
                }
            });
        } else {
            lvUsers.setAdapter(arrayAdapter);
            lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    intent = new Intent(SearchResultActivity.this, ViewProfileActivity.class);
                    intent.putExtra("key", index.get(i));
                    startActivity(intent);
                }
            });
        }

    }

    //Method if the user presses view all button below the entity.

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.action_view_all_facility:
                intent = new Intent(SearchResultActivity.this, DisplayAll.class);
                intent.putExtra("state", "0");
                startActivity(intent);
                break;

            case R.id.action_view_all_event:
                intent = new Intent(SearchResultActivity.this, DisplayAll.class);
                intent.putExtra("state", "1");
                startActivity(intent);
                break;

            case R.id.action_view_all_user:
                intent = new Intent(SearchResultActivity.this, DisplayAll.class);
                intent.putExtra("state", "2");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}