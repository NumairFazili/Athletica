package com.example.athletica.View.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.athletica.R;
import com.example.athletica.Controller.DisplayController;
import com.example.athletica.Controller.NetworkStatus;
import com.example.athletica.Model.Event;
import com.example.athletica.Controller.DataManager;
import com.example.athletica.View.event.CreateEventActivity;
import com.example.athletica.View.profile.ViewProfileActivity;
import com.example.athletica.View.search.Layout_mainpage;
import com.example.athletica.View.search.SearchResultActivity;
import com.example.athletica.View.settings.SettingsActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private ImageButton btnMenu;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private HomeEventRecyclerViewAdapter homeEventRecyclerViewAdapter;
    private List<Event> eventList = new ArrayList<Event>();
    private EditText etSearch;

    private DataManager dataManager;
    private DisplayController displayController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        networkCheck();
        btnMenu = findViewById(R.id.action_menu);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view_home);
        recyclerView = findViewById(R.id.rv_home);
        etSearch = findViewById(R.id.editTextSearch);


        dataManager = new DataManager();


        setupNavigationView();
        setupRecyclerView();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Intent intent = new Intent(HomeActivity.this, SearchResultActivity.class);
                intent.putExtra("query", etSearch.getText().toString());
                startActivity(intent);
                //finish();
                return true;
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });


    }


    private void networkCheck(){
        if(!NetworkStatus.isConnected(this)){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            Toast.makeText(getApplicationContext(), "You are not connected to the Internet. Connect and restart the app!", Toast.LENGTH_LONG).show();
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }


    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.END))
            this.drawer.closeDrawer(GravityCompat.END);
        else
            super.onBackPressed();
    }


    @Override
    protected void onResume() {
        networkCheck();
        super.onResume();
        // Uncheck navigation drawer menu item
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            if (navigationView.getMenu().getItem(i).isChecked())
                navigationView.getMenu().getItem(i).setChecked(false);
        }

    }


    // Setup the navigation drawer
    private void setupNavigationView() {
        // Setting NavigationItemSelectedListener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item was being clicked and perform the appropriate action
                switch (item.getItemId()) {
                    case R.id.nav_create_event:
                        item.setChecked(false);
                        startActivity(new Intent(HomeActivity.this, CreateEventActivity.class));
                        //finish();
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_view_profile:
                        item.setChecked(false);
                        startActivity(new Intent(HomeActivity.this, ViewProfileActivity.class));
                        //finish();
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_settings:
                        item.setChecked(false);
                        startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                        //finish();
                        drawer.closeDrawers();
                        return true;

                    default:
                        return false;

                }
            }
        });


        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Uncheck navigation drawer menu item
                for (int i = 0; i < navigationView.getMenu().size(); i++)
                    if (navigationView.getMenu().getItem(i).isChecked())
                        navigationView.getMenu().getItem(i).setChecked(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    public void onStart() {
        super.onStart();
        displayController = new DisplayController(this, 1);
        displayController.getEvents(this);

    }

    public void initRecyclerView(int id, ArrayList<String> names, ArrayList<String> index) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView recyclerView = findViewById(R.id.rv_home);

        recyclerView.setLayoutManager(layoutManager);

        Layout_mainpage adapter = new Layout_mainpage(this, names, index, id);

        recyclerView.setAdapter(adapter);

    }


    // Setup the recyclerView
    private void setupRecyclerView() {
        homeEventRecyclerViewAdapter = new HomeEventRecyclerViewAdapter(eventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeEventRecyclerViewAdapter);

        loadEventData();

        homeEventRecyclerViewAdapter.notifyDataSetChanged();

    }


    private void loadEventData() {

    }

}
