package com.example.athletica.data.facility;

import android.content.Context;
import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;

/**
 * This class implements the FacilityManager control that
 * manages the adding of comments and ratings for the facility.
 *
 * @author
 * @version 1.0
 * @since
 */
public class FacilityManager {

    /**
     * The Facility index.
     */
    String facilityIndex;
    /**
     * The Facility object.
     */
    Facility facility;

    /**
     * Instantiates a new Facility manager.
     *
     * @param context       the context
     * @param facilityIndex the facility index
     */
    public FacilityManager(Context context, String facilityIndex) {
        facility = new Facility(context, facilityIndex);
        this.facilityIndex = facilityIndex;
    }


    /**
     * Adds a new comment about the facility, returns true if comment is successfully added.
     *
     * @param comment comment about the facility
     */
    public boolean addComment(String comment) {
        DatabaseReference Comments_DB_Reference = facility.getComments_DB_Reference();
        String userid = facility.getUserid();
        String comment_content = comment;
        if (!TextUtils.isEmpty(comment_content)) {
            String id = Comments_DB_Reference.push().getKey();
            Comment comments_ = new Comment(userid, comment_content);
            Comments_DB_Reference.child(facilityIndex).child(id).setValue(comments_);
            return true;

        }
        return false;


    }


    /**
     * Adds a new rating about the facility, returns true if the rating is successfully added.
     */
    public boolean addRatings() {
        return true;
    }

}
