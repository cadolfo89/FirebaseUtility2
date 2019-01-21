package com.example.firebaseutility2.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.firebaseutility2.MainActivity;

import static com.example.firebaseutility2.util.FirebaseUtility.Obj;
import static com.example.firebaseutility2.util.FirebaseUtility.createNotificationChannel;

public class Notification {
    public static void notifica(Context context){

        Intent detailsIntent = new Intent(context, MainActivity.class);
        createNotificationChannel(Obj);
        PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                context,
                0,
                detailsIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle(Constants.TITLE)
                .setContentText("Nuova Richiesta")
                .setContentIntent(detailsPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false);

        NotificationManagerCompat.from(context).notify(1, mBuilder.build());
    }
}
