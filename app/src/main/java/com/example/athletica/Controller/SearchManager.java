package com.example.athletica.Controller;

import android.content.Context;

import com.example.athletica.Model.Facility;
import com.example.athletica.View.profile.ViewProfileActivity;
import com.example.athletica.View.search.SearchResultActivity;
import java.util.ArrayList;
import java.util.Map;



/**
 * This class is used for Retrieving the entities based on search input using DataManager
 */

public class SearchManager {
    private DataManager dataManager;
    private FilterManager filterManager;
    private Context context;
    private ArrayList<String> facilityName = new ArrayList<>(); // names of all the facilities are stored in this list
    private ArrayList<String> facilityIds = new ArrayList<>();
    private ArrayList<String> eventsName = new ArrayList<String>();// unique indexes of all the records are stored in this list
    private ArrayList<String> eventIds = new ArrayList<>();
    private ArrayList<String> userName = new ArrayList<String>();// unique indexes of all the records are stored in this list
    private ArrayList<String> userIds = new ArrayList<>();
    private ArrayList<Facility> facilityMap;
    private ArrayList<Map> eventMap;
    private ArrayList<Map> userMap;
    private String value;

    public SearchManager(Context context, String value) {
        this.context = context;
        this.value = value;
        dataManager = new DataManager();
        filterManager = new FilterManager();
    }


    /**
     *
     * @param searchResultActivity
     * Fetches  Facilities matching given input from DataManager and displays them in SearchResultActivity Class
     */

    public void getFacilities(final SearchResultActivity searchResultActivity) {
        facilityMap = dataManager.readDataAll(context, value);
        for (Facility facility : facilityMap) {
            String str2 = facility.getName();  //
            String index = facility.getFacilityIndex();
            facilityName.add(str2);
            facilityIds.add(index);
        }
        searchResultActivity.init_ListView(facilityName, facilityIds, 0);
    }

    /**
     *
     * @param viewProfileActivity
     * Fetches Events matching created by a particular user from DataManager and displays them in ViewProfile Class
     */

    public void getEvents(final ViewProfileActivity viewProfileActivity, String userId) {
        dataManager.getEventKeys(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                eventMap = ((ArrayList<Map>) object);
                filterManager.endEventCheck(eventMap);

                dataManager.getUserEvents(new DataManager.DataStatus() {
                    @Override
                    public void dataLoaded(Object object) {
                        ArrayList<String> userEvents = (ArrayList<String>) object;
                        for (Map<String, String> map : eventMap) {
                            String str1 = map.get("key");
                            if(userEvents.contains(str1)) {
                                String str2 = map.get("name");
                                eventIds.add(str1);
                                eventsName.add(str2);
                            }
                        }
                        viewProfileActivity.init_ListView(eventsName, eventIds);
                    }
                }, userId);

            }
        }, value);
    }


    /**
     *
     * @param searchResultActivity
     * Fetches  Events matching given input from DataManager and displays them in SearchResultActivity Class
     */

    public void getEvents(final SearchResultActivity searchResultActivity) {
        dataManager.getEventKeys(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                eventMap = ((ArrayList<Map>) object);
                filterManager.endEventCheck(eventMap);
                for (Map<String, String> map : eventMap) {
                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    eventIds.add(str1);
                    eventsName.add(str2);
                }
                searchResultActivity.init_ListView(eventsName, eventIds, 1);
            }
        }, value);
    }

    /**
     *
     * @param searchResultActivity
     * Fetches  Users matching given input from DataManager and displays them in SearchResultActivity Class
     */


    public void getUsers(final SearchResultActivity searchResultActivity) {
        dataManager.getAllUsers(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                userMap = ((ArrayList<Map>) object);
                for (Map<String, String> map : userMap) {
                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    userIds.add(str1);
                    userName.add(str2);
                }
                searchResultActivity.init_ListView(userName, userIds, 2);
            }
        }, value);

    }


}
