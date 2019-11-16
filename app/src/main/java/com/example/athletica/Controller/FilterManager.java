package com.example.athletica.Controller;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.athletica.Model.Facility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class FilterManager {


    /**
     * @param Emap
     * Check if event has passed based on current Date
     */
    public void endEventCheck(ArrayList<Map> Emap) {
        SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date mapDate = null;
        Date currentDate = new Date();

        ArrayList<Map> remove = new ArrayList<>();
        for (Map<String, String> map : Emap) {
            try {
                mapDate = dfParse.parse(map.get("endDate"));
                currentDate = dfParse.parse(dfParse.format(currentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (currentDate.compareTo(mapDate) == 1)
                remove.add(map);
        }

        for (Map<String, String> map : remove)
            Emap.remove(map);
    }

    /**
     * @param Emap
     * Returns Sorted Events
     */

    public void sortEvents(ArrayList<Map> Emap) {
        Collections.sort(Emap, new Comparator<Map>() {
            @Override
            public int compare(Map map1, Map map2) {
                SimpleDateFormat dfParse = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date map1Date = null;
                Date map2Date = null;
                try {
                    map1Date = dfParse.parse((String) map1.get("startDate"));
                    map2Date = dfParse.parse((String) map2.get("startDate"));


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return map1Date.compareTo(map2Date);

            }
        });
    }

    /**
     *
     * @param Emap
     * @param truncate
     * Returns Events List of desired size
     */

    public void truncateEvents(ArrayList<Map> Emap, int truncate) {

        if (Emap.size() < truncate)
            return;
        while (Emap.size() > truncate)
            Emap.remove(Emap.size() - 1);


    }

    /**
     *
     * @param facilities
     * @return facilityList in Sorted Order
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Facility> sortFacilityByName(ArrayList<Facility> facilities) {
        facilities.sort(Comparator.comparing(Facility::getName));
        return facilities;
    }

}
