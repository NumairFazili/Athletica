package com.example.athletica.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.athletica.Model.Event;
import com.example.athletica.Model.User;
import com.example.athletica.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Manager for Event class
 */
public class EventManager {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private String displayDateFormat = "dd/MM/yyyy HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(displayDateFormat, Locale.getDefault());

    public EventManager(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Validate details before creating event
     * @param name name of event
     * @param discipline discipline of event
     * @param description description of event
     * @param location location of event
     * @param maxParticipants maximum number of participants
     * @param price price of event
     * @param dateStart date when event starts
     * @param dateEnd date when event ends
     * @return true if details are valid, false else
     */
    public boolean validateDetails(String name, String discipline, String description, String location, int maxParticipants, double price, String dateStart, String dateEnd) {
        Date currentDate = new Date();
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(dateStart);
            endDate = sdf.parse(dateEnd);
            currentDate = sdf.parse(sdf.format(currentDate));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Problem with dates at the beginning!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(context, context.getString(R.string.empty_name), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(discipline)) {
            Toast.makeText(context, context.getString(R.string.empty_discipline), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(location)) {
            Toast.makeText(context, context.getString(R.string.empty_discipline), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (name.length() > 50) {
            Toast.makeText(context, "Name too long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (description.length() > 150) {
            Toast.makeText(context, "Description too long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (startDate.after(endDate)) {
            Toast.makeText(context, "Event cannot start after it is finished", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (startDate.before(currentDate) || startDate.equals(currentDate)) {
            Toast.makeText(context, "Problem with dates! start", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (endDate.before(currentDate) || endDate.equals(currentDate)) {
            Toast.makeText(context, "Problem with dates! end", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (price < 0) {
            Toast.makeText(context, "Price cannot be negative!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (maxParticipants < 0) {
            Toast.makeText(context, "Maximium participants number cannot be negative", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    /**
     * Save event with given details to Firebase
     * @param name
     * @param discipline
     * @param description
     * @param location
     * @param maxParticipants
     * @param price
     * @param startDate
     * @param endDate
     * @return
     */
    public boolean saveEvent(String name, String discipline, String description, String location, int maxParticipants, double price, String startDate, String endDate) {
        // Retrieve the ket for event_info
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("events_info");
        String key = firebaseDatabase.getReference().child("events_info").push().getKey();

        // Initialise a new event object
        Event event = new Event(name, location, startDate, endDate, description, discipline, LoginRegisterManager.loggedUser.getProfile().getName(), maxParticipants, price, 0);

        // Adds the new event into the database
        databaseReference.child(key).setValue(event);
        Toast.makeText(context, "Event saved", Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * Join user to event
     * @param event event to be joined
     * @param user user to join
     * @return true if success, false else
     */
    public boolean joinEvent(Event event, User user) {
        if (event.canBeJoined() && user.canJoin(event.getId())) {
            event.addNewParticipant();
            user.addEventToList(event.getId());
            Log.i("Join event", "New user joined event!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraw user from event
     * @param event event to withdraw from
     * @param user user to withdraw
     * @return true if success, false else
     */
    public boolean withdrawFromEvent(Event event, User user){
       if(!user.canJoin(event.getId())) {
            event.removeParticipant();
            user.removeEventFromList(event.getId());
            Log.i("Join event", "New user joined event!");
            return true;
        } else {
            return false;
        }
    }
}
