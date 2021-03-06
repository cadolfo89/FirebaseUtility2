package com.example.firebaseutility2.util;


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
import com.example.firebaseutility2.MainActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FirebaseUtility {

    public static final String BASE_URL = "https://prova-848a3.firebaseio.com/Utenti/";
    public static FirebaseDatabase database;
    public static String username;
    public static Context contesto;
    public static NotificationChannel channel;
    public static Object Obj;
    public static String stringa;

    public static String getRelativeUrl(String mail) {

        String relativeurl;
        String[] substring = mail.split("@");
        relativeurl = substring[0].replace('.', '_');
        return relativeurl;
    }


    public static void setPersonalListener(final String mail, final Context context) {
        contesto = context;
        username = getRelativeUrl(mail);
        database = FirebaseDatabase.getInstance();
        DatabaseReference utenteRoot = database.getReferenceFromUrl(BASE_URL + username);

        utenteRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                com.example.firebaseutility2.util.Notification.notifica(context);
                
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.i("modifica", "modifica database");
                Notification.notifica(context);


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

    public static void createNotificationChannel(Object oggetto) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifica";
            String description = "AGGIORNAMENTO";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) oggetto;
            notificationManager.createNotificationChannel(channel);

        }
    }
}

