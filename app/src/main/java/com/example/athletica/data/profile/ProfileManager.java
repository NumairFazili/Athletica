package com.example.athletica.data.profile;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * This class implements the ProfileManager control that
 * manages the following and unfollowing of users.
 *
 * @author
 * @version 1.0
 * @since
 */
public class ProfileManager {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private String displayDateFormat = "dd/MM/yyyy HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(displayDateFormat, Locale.getDefault());

    /**
     * Instantiates a new Profile manager.
     *
     * @param context the context
     */
    public ProfileManager(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    //public boolean beingFollowed()
    //{

    //}
}