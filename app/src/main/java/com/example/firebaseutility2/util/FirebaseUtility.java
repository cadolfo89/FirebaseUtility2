package com.example.firebaseutility2.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;


import com.example.firebaseutility2.MainActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.FirebaseAppHelper;

import static android.widget.Toast.LENGTH_LONG;


public class FirebaseUtility {

    public static final String BASE_URL = "https://prova-848a3.firebaseio.com/Utenti/";
    public static FirebaseDatabase database;
    public static String username;
    public static Context contesto;
    public static NotificationChannel channel;
    public static Object Obj;
    public static  String stringa;

    public static String getRelativeUrl(String mail) {
        String relativeurl;
        String mailoriginal;
        String mailmodified;
        mailoriginal = mail;
        String[] substring = mailoriginal.split("@");
        mailmodified = substring[0];
        relativeurl = mailmodified.replace('.', '_');

        stringa=relativeurl;
        return relativeurl;
    }


    public static void setPersonalListener(final String mail, final Context context, final Object oggetto) {
contesto=context;
Obj=oggetto;

         username = getRelativeUrl(mail);
        database = FirebaseDatabase.getInstance();

        DatabaseReference utenteRoot = database.getReferenceFromUrl(BASE_URL + username);

        utenteRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.i("modifica", "modifica database");
                //NotificationManagerCompat notificationManager=NotificationManagerCompat.from();
                Intent detailsIntent = new Intent(context, MainActivity.class);
                 createNotificationChannel(Obj);

                PendingIntent detailsPendingIntent = PendingIntent.getActivity(
                        context,
                        0,
                        detailsIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(Constants.TITLE)
                        .setContentText(Constants.DB_UPDATED)
                        .setContentIntent(detailsPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

                NotificationManagerCompat.from(context).notify(1,mBuilder.build());

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void addRequest(String mail) {
        database = FirebaseDatabase.getInstance();
         username = getRelativeUrl(mail);
        DatabaseReference utenteadd = database.getReferenceFromUrl(BASE_URL);
        utenteadd.child(username).child("new").child("Stato").setValue("pending");

    }
    public static  void createNotificationChannel(Object oggetto){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifica a capocchia";
            String description ="AGGIORNAMENTO";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
              NotificationManager notificationManager =(NotificationManager)oggetto;
             notificationManager.createNotificationChannel(channel);

        }
    }
}

