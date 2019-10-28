package com.example.athletica.data.user;

import java.util.ArrayList;

/**
 * This class implements the UserProfile entity
 * with the attributes id, name, gender, birthdate, description, follows, sportPreferences
 *
 * @author
 * @version 1.0
 * @since
 */
public class UserProfile {

    private String id, name, gender, birthdate, description;
    private ArrayList<String> follows;
    private ArrayList<String> sportPreferences;

    /**
     * Instantiates a new User profile.
     */
    public UserProfile() {

    }

    /**
     * Instantiates a new User profile.
     *
     * @param name             the name
     * @param gender           the gender
     * @param description      the description
     * @param birthdate        the birthdate
     * @param sportPreferences the sport preferences
     * @param follows          the follows
     */
    public UserProfile(String name, String gender, String description, String birthdate, ArrayList<String> sportPreferences, ArrayList<String> follows) {
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.birthdate = birthdate;
        this.sportPreferences = sportPreferences;
        this.follows = follows;
    }

    /**
     * Returns a String containing the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a String containing the user's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns a String containing the user's date of birth.
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Returns a String containing a short description of the user.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an ArrayList of Strings containing the user's sport preferences.
     */
    public ArrayList<String> getSportPreferences() {
        return sportPreferences;
    }

    /**
     * Returns a ArrayList of Strings containing the users' name that follows the user.
     */
    public ArrayList<String> getFollows() {
        return follows;
    }

    /**
     * Sets the user's date of birth
     *
     * @param birthdate user's date of birth
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Sets the user's description
     *
     * @param description short description of the user
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the user's id
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets sport preferences.
     *
     * @param sportPreferences the sport preferences
     */
    public void setSportPreferences(ArrayList<String> sportPreferences) {
        this.sportPreferences = sportPreferences;
    }


    public void setFollows(ArrayList<String> follows) {
        this.follows = follows;
    }
}