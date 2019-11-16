package com.example.athletica.Controller;

import android.content.Context;
import android.text.TextUtils;

import com.example.athletica.Model.Comments;
import com.example.athletica.Model.Facility;
import com.example.athletica.Model.Ratings;
import com.google.firebase.database.DatabaseReference;


/**
 * This class is used to manage the functions for View Facility Class
 */

public class FacilityManager {

    String facilityIndex;
    Facility facility;
    private DataManager dataManager;

    public FacilityManager(Context context, String facilityIndex) {
        this.facilityIndex = facilityIndex;
        dataManager = new DataManager();
        facility = dataManager.readIndex(context, facilityIndex);
    }

    /**
     *
     * @param submitted_rating
     * Adds rating to the firebase database for a given facility by a particular user
     */

    public void addRating(float submitted_rating) {
        DatabaseReference Ratings_DB_Ref = facility.getRatings_DB_Ref();
        String userid = facility.getUserid(); //getUserID of logged in user
        String id = Ratings_DB_Ref.push().getKey();

        Ratings ratings_;
        ratings_ = new Ratings(submitted_rating, id,userid);
        Ratings_DB_Ref.child(facilityIndex).child(userid).setValue(ratings_);
    }

    /**
     *
     * @param comment
     * Adds Comment to the firebase database for a given facility by a particular user
     */


    public boolean addComments(String comment) {
        DatabaseReference Comments_DB_Reference = facility.getComments_DB_Reference();

        String userName = LoginRegisterManager.loggedUser.getProfile().getName();
        String userid = facility.getUserid();
        String comment_content = comment;

        if (!TextUtils.isEmpty(comment_content)) {
            String id = Comments_DB_Reference.push().getKey();

            Comments comments_ = new Comments(userid, userName, comment_content);
            Comments_DB_Reference.child(facilityIndex).child(id).setValue(comments_);
            return true;

        }
        return false;

    }
}
