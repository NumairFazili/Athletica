package com.example.athletica.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Entity class that represents User
 * @author Kamil Rogoda
 */
public class User {
    /** Attributes of User class */
    private String id, email;
    private UserProfile profile;
    private ArrayList<String> followedUsers, followers;
    private ArrayList<String> eventsJoined;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     * Empty constructor
     */
    public User() {

    }

    /**
     * Constructor used to create new instance of class user
     * @param id user id
     * @param profile user profile with details
     * @param followedUsers array of followed users
     * @param followers array of follwoers
     */
    public User(String id, UserProfile profile, ArrayList<String> followedUsers, ArrayList<String> followers) {
        this.id = id;
        this.profile = profile;
        this.followedUsers = followedUsers;
        this.followers = followers;
    }

    /**
     * Return user's id
     * @return user's id
     */
    public String getId() {
        return id;
    }

    /**
     * Set user's id
     * @param id new user's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return user profile
     * @return object of type UserProfile
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Set user profile
     * @param profile new user profile
     */
    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    /**
     * Return email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user's email
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return array of followed users
     * @return array of followed users
     */
    public ArrayList<String> getFollowedUsers() {
        return followedUsers;
    }

    /**
     * Set follwoed users
     * @param followedUsers array of new followed users
     */
    public void setFollowedUsers(ArrayList<String> followedUsers) {
        this.followedUsers = followedUsers;
    }

    /**
     * Return array of followers
     * @return array of followers
     */
    public ArrayList<String> getFollowers() {
        return followers;
    }

    /**
     * Set user's followers
     * @param followers array of new followers
     */
    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    /**
     * Set events that user joined
     * @param eventsJoined array of joined events
     */
    public void setEventsJoined(ArrayList<String> eventsJoined) {
        this.eventsJoined = eventsJoined;
    }

    /**
     * Check if user can join event with given key
     * @param eventKey key of event to be checked
     * @return true if can, false else
     */
    public boolean canJoin(String eventKey) {
        return !eventsJoined.contains(eventKey);
    }

    /**
     * Add event key to joined events
     * @param eventKey key to be added
     */
    public void addEventToList(String eventKey) {
        eventsJoined.add(eventKey);
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("users_events").child(id);
        databaseReference.setValue(eventsJoined);
    }

    /**
     * Remove event from joined events
     * @param eventKey key of event to be removed
     */
    public void removeEventFromList(String eventKey){
        if(eventsJoined.contains(eventKey)){
            eventsJoined.remove(eventKey);
            DatabaseReference databaseReference = firebaseDatabase.getReference().child("users_events").child(id);
            databaseReference.setValue(eventsJoined);
        }
    }

}