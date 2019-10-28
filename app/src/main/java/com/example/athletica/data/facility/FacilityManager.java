package com.example.athletica.data.facility;

import android.content.Context;
import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;

public class FacilityManager {

    String facilityIndex;
    Facility facility;

    public FacilityManager(Context context, String facilityIndex) {
        facility=new Facility(context,facilityIndex);
        this.facilityIndex=facilityIndex;
    }





    public boolean addComments(String comment) {
        DatabaseReference Comments_DB_Reference=facility.getComments_DB_Reference();
        String userid=facility.getUserid();
        String comment_content = comment;
        if (!TextUtils.isEmpty(comment_content)) {
            String id = Comments_DB_Reference.push().getKey();
            Comments comments_ = new Comments(userid, comment_content);
            Comments_DB_Reference.child(facilityIndex).child(id).setValue(comments_);
            return true;

        }
        return false;


    }



}

