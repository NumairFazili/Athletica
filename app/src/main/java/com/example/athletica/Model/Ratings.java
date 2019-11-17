package com.example.athletica.Model;

public class Ratings {
    private String ratingID;
    private String userID;
    private float ratingContent;






    public Ratings(float ratingContent, String ratingID,String userID) {
        this.ratingContent = ratingContent;
        this.ratingID = ratingID;
        this.userID=userID;
    }

    public String getUserID() {
        return userID;
    }

    /**
     *
     * @returns rating of the facility
     */

    public float getRatingContent() {
        return ratingContent;
    }

}