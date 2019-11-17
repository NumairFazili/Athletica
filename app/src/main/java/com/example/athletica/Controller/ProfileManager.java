package com.example.athletica.Controller;

import android.content.Context;

import com.example.athletica.Model.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ProfileManager {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private Context context;
    UserProfile currentProfile;
    private LoginRegisterManager loginRegisterManager;

    public ProfileManager(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        loginRegisterManager = new LoginRegisterManager();
    }

    public String getCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser.getUid();
    }

    /**
     *
     * @param user
     * @return age of user
     */

    public static int calculateAge(UserProfile user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar birthday = Calendar.getInstance();
        try {
            birthday.setTime(simpleDateFormat.parse(user.getBirthdate()));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        if (birthday.get(Calendar.MONTH) > now.get(Calendar.MONTH))
            age--;
        else if (birthday.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
            if (birthday.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))
                age--;
        }
        return age;
    }

    /**
     *
     * @param selectedProfile
     * @param currentProfile
     * @param followers
     * @return  String with updated value of followers
     */

    public String follow(UserProfile selectedProfile, UserProfile currentProfile, String followers) {
        ArrayList<String> newFollows = currentProfile.getFollows();
        String id = selectedProfile.getId();
        String newFollowers;

        newFollows.add(selectedProfile.getId());
        if (selectedProfile.getFollowers() == null) {
            newFollowers = "1";
        } else {
            newFollowers = String.valueOf(Integer.parseInt(followers) + 1);
        }

        loginRegisterManager.follow(newFollows, id, newFollowers);
        return String.valueOf(Integer.parseInt(followers) + 1);
    }


    /**
     *
     * @param selectedProfile
     * @param currentProfile
     * @param followers
     * @return String with updated value of followers
     */


    public String unfollow(UserProfile selectedProfile, UserProfile currentProfile, String followers) {
        ArrayList<String> newFollows = currentProfile.getFollows();
        String id = selectedProfile.getId();
        String newFollowers;

        for (int i = 0; i < newFollows.size(); i++) {
            if (newFollows.get(i).equals(id)) {
                newFollows.remove(i);
                break;
            }
        }
        newFollowers = String.valueOf(Integer.parseInt(followers) - 1);
        loginRegisterManager.follow(newFollows, id, newFollowers);
        return String.valueOf(Integer.parseInt(followers) - 1);
    }
}