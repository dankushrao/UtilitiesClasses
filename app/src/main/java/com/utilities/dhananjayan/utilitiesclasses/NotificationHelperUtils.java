package com.utilities.dhananjayan.utilitiesclasses;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dhananjayan on 11/23/2015.
 */
public class NotificationHelperUtils {

    /**
     * displays a notification in the system status bar
     * @param headline - headline to display in notification bar
     * @param body - body of notification (when user expands bar)
     * @param context
     * @param id - unique (within app) ID of notification
     */
    public static void fireNotification(String headline,String body,Context context,int id,Integer iconId){
        String ns=Context.NOTIFICATION_SERVICE;
        NotificationManager notifcationMgr=(NotificationManager)context.getSystemService(ns);
        int icon=R.drawable.ic_launcher;
        if (iconId != null) {
            icon=iconId;
        }
        Notification notification=new Notification(icon,headline,System.currentTimeMillis());
        Intent notificationIntent=new Intent(context,MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,notificationIntent,0);
        notification.setLatestEventInfo(context,headline,body,contentIntent);
        notifcationMgr.notify(id,notification);
    }

}
