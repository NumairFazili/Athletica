package com.example.athletica.Controller;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Class to check if device is connected to the internet
 */
public class NetworkStatus {
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
