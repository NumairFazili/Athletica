package com.example.athletica.data.search;

import android.content.Context;

import com.example.athletica.data.facility.Facility;
import com.example.athletica.data.user.DataManager;
import com.example.athletica.ui.search.SearchResultActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class SearchManager {


    public DataManager dataManager;
    Context context;
    private ArrayList<String> facilityName = new ArrayList<>(); // names of all the facilities are stored in this list
    private ArrayList<String> facilityIds = new ArrayList<>();
    private ArrayList<String> eventsName = new ArrayList<String>();// unique indexes of all the records are stored in this list
    private ArrayList<String> eventIds = new ArrayList<>();
    private ArrayList<String> userName = new ArrayList<String>();// unique indexes of all the records are stored in this list
    private ArrayList<String> userIds = new ArrayList<>();
    private ArrayList<Facility> facilities;
    private ArrayList<Map> eventMap;
    private ArrayList<Map> userMap;
    private String value;

    public SearchManager(Context context, String value) {
        this.context = context;
        this.value = value;
        dataManager = new DataManager();
    }


    public void getFacilities(final SearchResultActivity searchResultActivity) {
        facilities = (ArrayList<Facility>) dataManager.readDataAll(context, value);
        for (Facility facility : facilities) {
            String str2 = facility.getName();  //
            String index = facility.getFacilityIndex();
            facilityName.add(str2);
            facilityIds.add(index);
        }
        searchResultActivity.initListView(facilityName, facilityIds, 0);
    }


    public void getEvents(final SearchResultActivity searchResultActivity) {
        dataManager.getEventKeys(new DataManager.DataStatus() {
            @Override
            public void dataLoaded(Object object) {
                eventMap = ((ArrayList<Map>) object);
                for (Map<String, String> map : eventMap) {
                    String str1 = map.get("key");
                    String str2 = map.get("name");
                    eventIds.add(str1);
                    eventsName.add(str2);
                }
                searchResultActivity.initListView(eventsName, eventIds, 1);
            }
        }, 20, value);

    }


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
                searchResultActivity.initListView(userName, userIds, 2);
            }
        }, value);

    }




}
