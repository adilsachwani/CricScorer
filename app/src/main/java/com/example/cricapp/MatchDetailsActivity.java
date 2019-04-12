package com.example.cricapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import firebase_models.Details;

public class MatchDetailsActivity extends AppCompatActivity {

    DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    EditText totalOversEditText, totalPlayerEditText, teamOneEditText, teamTwoEditText;
    Button createMatchButton;
    String match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("all").addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        totalOversEditText = findViewById(R.id.overEditText);
        totalPlayerEditText = findViewById(R.id.playersEditText);
        teamOneEditText = findViewById(R.id.teamOneEditText);
        teamTwoEditText = findViewById(R.id.teamTwoEditText);
        createMatchButton = findViewById(R.id.createMatchButton);

        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int total_matches = dataSnapshot.child("total_matches").getValue(Integer.class) + 1;
                match = "match" + total_matches;
                dataSnapshot.child("total_matches").getRef().setValue(total_matches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        createMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int overs = Integer.parseInt(totalOversEditText.getText().toString());
                int players = Integer.parseInt(totalPlayerEditText.getText().toString());
                String teamOne = teamOneEditText.getText().toString();
                String teamTwo = teamTwoEditText.getText().toString();

                dbReference.child(match).child("details").setValue(new Details(overs,players,teamOne,teamTwo,true));

                Intent intent = new Intent(MatchDetailsActivity.this, TeamActivity.class);
                startActivity(intent);

            }
        });



    }

}