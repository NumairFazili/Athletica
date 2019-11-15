package com.example.athletica.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.athletica.R;
import com.example.athletica.data.account.LoginRegisterManager;
import com.example.athletica.data.profile.ProfileManager;
import com.example.athletica.data.search.SearchManager;
import com.example.athletica.data.user.DataManager;
import com.example.athletica.data.user.UserProfile;
import com.example.athletica.ui.event.ViewEventActivity;
import com.example.athletica.ui.search.SearchResultActivity;
import com.example.athletica.data.DisplayAll.DisplayController;
import com.example.athletica.ui.search.Layout_mainpage;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class ViewProfileActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvName, tvGenderAge, tvBio, tvInterests, tvFollowers, tvComingUp;
    ExtendedFloatingActionButton btnFollowUpdate;
    ListView rvComingUp;

    private DataManager dataManager;
    private ProfileManager profileManager;
    private LoginRegisterManager loginRegisterManager;
    private String index;
    private UserProfile selectedProfile;
    private UserProfile currentProfile;
    private DisplayController displayController;

    private Intent intent;
    private SearchManager searchManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        tvName = findViewById(R.id.tvName);
        tvGenderAge = findViewById(R.id.tvGenderAge);
        tvBio = findViewById(R.id.tvBio);
        tvInterests = findViewById(R.id.tvInterests);
        tvFollowers = findViewById(R.id.tvFollowers);


        rvComingUp = findViewById(R.id.rvComingUp);
        //tvComingUp = findViewById(R.id.tvComingUp);

        btnFollowUpdate = findViewById(R.id.action_edit);

        index = getIntent().getStringExtra("key");

        if (TextUtils.isEmpty(index)) {
            index = LoginRegisterManager.loggedUser.getId();
        }
        dataManager = new DataManager();
        loginRegisterManager = new LoginRegisterManager();
        profileManager = new ProfileManager(getApplicationContext());
        intent = getIntent();
        searchManager = new SearchManager(this, "");
        getDetails();

        btnFollowUpdate.setOnClickListener(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        this.recreate();
    }

    private void getDetails() {
        dataManager.getProfileByKey(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                selectedProfile = (UserProfile) object;
                selectedProfile.setId(index);
                populate(selectedProfile);
            }
        }, index);
    }

    private void populate(UserProfile profile) {
        if (!index.equals(LoginRegisterManager.loggedUser.getId())) {
            btnFollowUpdate.setIcon(null);
            btnFollowUpdate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_24px, 0, 0, 0);
            setFollowButton(profile.getId());
        }
        tvName.setText(profile.getName());
        tvGenderAge.setText(profile.getGender() + ", " + ProfileManager.calculateAge(profile));
        tvBio.setText(profile.getDescription());
        tvFollowers.setText(profile.getFollowers());
        if (tvFollowers.getText().equals(""))
            tvFollowers.setText("0");

        String allInterests = "";
        for (String nextInterest : profile.getSportPreferences()) {
            allInterests += nextInterest + "\n";
        }
        tvInterests.setText(allInterests);

        System.out.println(profile.getId());
        searchManager.getEvents(this, profile.getId());
    }

    private void setFollowButton(final String selectedUId) {
        dataManager.getProfileByKey(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                currentProfile = (UserProfile) object;
                currentProfile.setId(profileManager.getCurrentUser());

                for (String nextFollow : currentProfile.getFollows()) {
                    if (nextFollow.equals(selectedUId))
                        btnFollowUpdate.setText("UNFOLLOW");
                    else
                        btnFollowUpdate.setText("FOLLOW");

                }
            }
        }, profileManager.getCurrentUser());
    }

    @Override
    public void onClick(View view) {
        String button = ((ExtendedFloatingActionButton) findViewById(view.getId())).getText().toString();
        if (button.equals("FOLLOW")) {
            tvFollowers.setText(profileManager.follow(selectedProfile, currentProfile, tvFollowers.getText().toString()));
            btnFollowUpdate.setText("UNFOLLOW");
        } else if (button.equals("UNFOLLOW")) {
            tvFollowers.setText(profileManager.unfollow(selectedProfile, currentProfile, tvFollowers.getText().toString()));
            btnFollowUpdate.setText("FOLLOW");
        } else {
            Intent intent = new Intent(getApplicationContext(), CreateProfileActivity.class);
            startActivity(intent);
        }
    }

    public void init_ListView(ArrayList<String> names, final ArrayList<String> index) {
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);


        rvComingUp.setAdapter(arrayAdapter);
        rvComingUp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(ViewProfileActivity.this, ViewEventActivity.class);
                intent.putExtra("key", index.get(i));
                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
    }
}
