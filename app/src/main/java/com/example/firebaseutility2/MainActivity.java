package com.example.firebaseutility2;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebaseutility2.util.FirebaseUtility;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    String emailTemp;
    EditText testomail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          testomail= findViewById(R.id.mail);
        Button invia=findViewById(R.id.send);
        Button listener=findViewById(R.id.setListener);


       //FirebaseDatabase database=FirebaseDatabase.getInstance();


        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTemp = testomail.getText().toString();
                FirebaseUtility.addRequest(emailTemp);
            }
        });
        listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTemp = testomail.getText().toString();
                FirebaseUtility.setPersonalListener(emailTemp,MainActivity.this,getSystemService(NotificationManager.class));
               /* Notification.createNotificationChannel();
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(Notification.channel);
                Notification.CreaNotifica(MainActivity.this,Notification.CreatePendingIntent(MainActivity.this));*/


            }
        });



    }
}



