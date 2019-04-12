package com.example.cricapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import firebase_models.Details;
import firebase_models.Player;

public class TeamActivity extends AppCompatActivity {

    String match = "match1";
    LinearLayout linearLayout;
    TextView teamNameText;
    Button nextButton;
    List<EditText> editTextsList = new ArrayList<>();
    boolean teamDone = false;
    String team = "team1";

    DatabaseReference matchReference = FirebaseDatabase.getInstance().getReference().child(match);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        linearLayout = findViewById(R.id.playersLayout);
        teamNameText = findViewById(R.id.teamNameText);
        nextButton = findViewById(R.id.nextButton);

        matchReference.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Details d = dataSnapshot.getValue(Details.class);

                displayTeamName(d.getTeamOne());
                populateFields(d.getTotalPlayers());

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for(int i=0; i<d.getTotalPlayers(); i++){

                            String name = editTextsList.get(i).getText().toString();
                            int positon = i+1;

                            Player player = new Player(name,positon);

                            matchReference.child("teams").child(team).child(name).setValue(player);

                        }

                        if(teamDone){
                            Intent intent = new Intent(TeamActivity.this, MatchScoreActivity.class);
                            startActivity(intent);
                        }
                        else {
                            teamDone = true;
                            displayTeamName(d.getTeamTwo());
                            team = "team2";

                            for(int i=0; i<d.getTotalPlayers(); i++){
                                editTextsList.get(i).getText().clear();
                            }

                        }

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void displayTeamName(String teamName){
        teamNameText.setText(teamName);
    }

    void populateFields(int totalPlayers){

        for(int i=0; i<totalPlayers; i++){

            EditText playerEditText = new EditText(TeamActivity.this);

            playerEditText.setHint("Enter name " + (i+1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            lp.setMargins(40,10,40,10);

            playerEditText.setLayoutParams(lp);
            editTextsList.add(playerEditText);

            linearLayout.addView(playerEditText);
        }


    }

}