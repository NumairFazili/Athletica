package com.example.athletica.data.user;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * This class implements the User entity
 * with the attributes id, profile, followedUsers, followers
 *
 * @author
 * @version 1.0
 * @since
 */
public class User {

    private String id, email;
    private UserProfile profile;
    private ArrayList<String> followedUsers, followers;
    private ArrayList<String> eventsJoined;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param id            the id
     * @param profile       the profile
     * @param followedUsers the followed users
     * @param followers     the followers
     */
    public User(String id, UserProfile profile, ArrayList<String> followedUsers, ArrayList<String> followers) {
        this.id = id;
        this.profile = profile;
        this.followedUsers = followedUsers;
        this.followers = followers;
    }

    /**
     * Returns a string containing the user's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user's id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the UserProfile object of the user.
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Set's the user's profile
     *
     * @param profile user's profile
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    /**
     * Returns a string containing the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns an ArrayList of Strings containing the users' name the user has followed.
     */
    public ArrayList<String> getFollowedUsers() {
        return followedUsers;
    }


    public void setFollowedUsers(ArrayList<String> followedUsers) {
        this.followedUsers = followedUsers;
    }

    /**
     * Returns an ArrayList of Strings containing the users' name following the user.
     */
    public ArrayList<String> getFollowers() {
        return followers;
    }


    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    /**
     * Sets events joined.
     *
     * @param eventsJoined the events joined
     */
    public void setEventsJoined(ArrayList<String> eventsJoined) {
        this.eventsJoined = eventsJoined;
    }

    /**
     * Returns true if the user is allowed to join an event.
     *
     * @param eventKey the event key
     */
    public boolean canJoin(String eventKey) {
        return !eventsJoined.contains(eventKey);
    }

    /**
     * Add the event joined to the list of events the user has already joined.
     *
     * @param eventKey the event key
     */
    public void addEventToList(String eventKey) {
        eventsJoined.add(eventKey);
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("users_events").child(id);
        databaseReference.setValue(eventsJoined);
    }

}