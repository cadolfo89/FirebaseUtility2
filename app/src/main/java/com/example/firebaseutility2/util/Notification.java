package com.example.firebaseutility2.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.firebaseutility2.MainActivity;
import com.example.firebaseutility2.R;

import static com.example.firebaseutility2.util.Constants.CHANNEL_ID;

public class Notification {

   static  Context contesto;
    public static NotificationChannel channel;





    public  static PendingIntent CreatePendingIntent(Context context){
   Intent detailsIntent = new Intent(context, MainActivity.class);

   PendingIntent detailsPendingIntent = PendingIntent.getActivity(
            context,
            0,
            detailsIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
           return detailsPendingIntent;

     }
    public static  void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifica a capocchia";
            String description ="AGGIORNAMENTO";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
             channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
          //  NotificationManager notificationManager = getSystemService(NotificationManager.class);
           // notificationManager.createNotificationChannel(channel);
        }
    }

    public static void CreaNotifica(Context context,PendingIntent detailsPendingIntent) {

    contesto=context;
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(Constants.TITLE)
            .setContentText(Constants.DB_UPDATED)
           // .setContentIntent(detailsPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false);

            //   NotificationManagerCompat.from(context).notify(1,mBuilder.build());
}}


