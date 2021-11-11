package com.example.solaprog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private String TAG = "Geofence Broadcast Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        //Toast.makeText(context, "Geofence Triggered ...", Toast.LENGTH_SHORT).show();

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent((intent));

        NotificationHelper notificationHelper = new NotificationHelper(context);

        if(geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: Error Geofence ...");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();


        //Location location = geofencingEvent.getTriggeringLocation();

        int transitionType = geofencingEvent.getGeofenceTransition();

        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "Geofence Enter ...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE TRANSITION ENTER","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "Geofence Dwell ...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE TRANSITION DWELL","",MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "Geofence Exit ...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("GEOFENCE TRANSITION EXIT","",MapsActivity.class);
                break;
        }
    }
}