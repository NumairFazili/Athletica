package com.example.athletica.data.event;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * This class implements the Event entity
 * with the attributes id, name, location, startDate, endDate, description, discipline, createdBy
 *
 * @author
 * @version 1.0
 * @since
 */
public class Event {

    private String id, name, location, startDate, endDate, description, discipline, createdBy;
    private int maxParticipants;
    private double price;
    private ArrayList<String> participants;
    private int currentParticipants;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    /**
     * Instantiates a new Event.
     */
    public Event() {

    }

    /**
     * Instantiates a new Event.
     *
     * @param name                event's name
     * @param location            event's location
     * @param startDate           event's start date
     * @param endDate             event's end date
     * @param description         event's description
     * @param discipline          event's discipline
     * @param createdBy           name of user the event is created by
     * @param maxParticipants     maximum number of participants allowed to join the event
     * @param price               price of the event
     * @param currentParticipants name of users that has joined the event
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
     * Returns a string containing the event's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the event's id using the parameter id.
     *
     * @param id the id of the event
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a string containing the name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string containing the location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns a string containing the start date of the event.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns a string containing the end date of the event.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns a string containing the description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string containing the discipline of the event.
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * Returns a string containing the name of the user the event is created by.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Returns an int of the maximum number of participants allowed to join the event.
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Returns the price of the event.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns true of the event can be joined.
     */
    public boolean canBeJoined() {
        return currentParticipants < maxParticipants || maxParticipants == 0;
    }

    /**
     * Add new participant to an event.
     */
    public void addNewParticipant() {
        currentParticipants++;
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("events_info")
                .child(id)
                .child("currentParticipants");
        databaseReference.setValue(currentParticipants);
    }
}
