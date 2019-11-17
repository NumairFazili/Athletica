package com.example.athletica.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Model class which represents Event with getters and setters.
 *
 * @author Kamil Rogoda
 *
 */
public class Event {

    /** Attributes of Event class */
    private String id, name, location, startDate, endDate, description, discipline, createdBy;
    private int maxParticipants;
    private double price;
    private ArrayList<String> participants;
    private int currentParticipants;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     * Empty constructor
     */
    public Event() {

    }

    /**
     * Event constructor used to create new instance of class Event.
     *
     * @param name name of Event
     * @param location location of Event
     * @param startDate date when Event starts
     * @param endDate date when Event ends
     * @param description short descritpion of the Event
     * @param discipline discipline of Event
     * @param createdBy author of the Event
     * @param maxParticipants maximim amount of participants
     * @param currentParticipants current amout of participants registered for this Event
     * @param price price of the Event
     */
    public Event(String name, String location, String startDate, String endDate, String description, String discipline, String createdBy, int maxParticipants, double price, int currentParticipants) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.discipline = discipline;
        this.createdBy = createdBy;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.currentParticipants = currentParticipants;
    }

    /**
     * Return event's id.
     *
     * @return id of Event
     */
    public String getId() {
        return id;
    }

    /**
     * Set event's id.
     *
     * @param id new id of event
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Return the location of the event.
     *
     * @return the location of the event
     */
    public String getLocation() {
        return location;
    }


    /**
     * Return date when event starts
     *
     * @return date when event starts
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Return date when event ends
     *
     * @return date when event ends
     */
    public String getEndDate() {
        return endDate;
    }


    /**
     * Return the description of the event
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }


    /**
     * Return the discipline of the event
     *
     * @return the discipline of the event
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * Return the author of the event
     *
     * @return author of the event
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Return maximum number of event's participants
     *
     * @return maximum number of event's participants
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Return the price of the event
     *
     * @return price of the event
     */
    public double getPrice() {
        return price;
    }

    /**
     * Check if event can be joined
     *
     * @return true if can be joined, false else
     */
    public boolean canBeJoined() {
        return currentParticipants < maxParticipants || maxParticipants == 0;
    }

    /**
     * Add new participant to the event
     *
     */
    public void addNewParticipant() {
        currentParticipants++;
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("events_info")
                .child(id)
                .child("currentParticipants");
        databaseReference.setValue(currentParticipants);
    }

    /**
     * Remove participant form the event
     */
    public void removeParticipant(){
        currentParticipants--;
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("events_info")
                .child(id)
                .child("currentParticipants");
        databaseReference.setValue(currentParticipants);
    }
}
