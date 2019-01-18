package com.example.firebaseutility2.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


    public static String getRelativeUrl(String mail) {
        String relativeurl;
        String mailoriginal;
        String mailmodified;
        mailoriginal = mail;
        String[] substring = mailoriginal.split("@");
        mailmodified = substring[0];
        relativeurl = mailmodified.replace('.', '_');

        return relativeurl;
    }


    public static void setPersonalListener(final String mail, final Context context) {
contesto=context;

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
                        Notification.CreaNotifica(context,Notification.CreatePendingIntent(context));

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
}
