package com.example.cricapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class TeamDetailsActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView teamNameText;
    Button nextButton;
    List<EditText> editTextsList = new ArrayList<>();
    boolean teamDone = false;
    String team = "team1";
    String match;
    TextView toolbarText;
    ImageView toolbarBackButton;

    DatabaseReference matchReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        linearLayout = findViewById(R.id.playersLayout);
        nextButton = findViewById(R.id.nextButton);

        match = getIntent().getStringExtra("match_no");
        Log.d("adil" ,"match: " +match);

        matchReference = FirebaseDatabase.getInstance().getReference().child("matches").child(match);

        matchReference.child("details").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Details d = dataSnapshot.getValue(Details.class);

                setUpToolbar(d.getTeamOne() + " Squad");
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
                            Intent intent = new Intent(TeamDetailsActivity.this, MatchScorerActivity.class);
                            intent.putExtra("match_no",match);
                            startActivity(intent);
                        }
                        else {
                            teamDone = true;
                            setUpToolbar(d.getTeamTwo() + " Squad");
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

    private void displayTeamName(String teamName){
        teamNameText.setText(teamName);
    }

    private void populateFields(int totalPlayers){

        for(int i=0; i<totalPlayers; i++){

            EditText playerEditText = new EditText(TeamDetailsActivity.this);

            playerEditText.setHint("Enter name " + (i+1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            lp.setMargins(0,0,0,20);

            playerEditText.setLayoutParams(lp);
            editTextsList.add(playerEditText);

            linearLayout.addView(playerEditText);
        }


    }

    private void setUpToolbar(String title){
        toolbarText = findViewById(R.id.toolrbarText);
        toolbarBackButton = findViewById(R.id.toolbarButton);

        toolbarText.setText(title);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}