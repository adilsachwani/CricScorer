package com.example.cricapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import firebase_models.Details;

public class MatchDetailsActivity extends AppCompatActivity {

    String match = "match1";

    DatabaseReference matchReference = FirebaseDatabase.getInstance().getReference().child(match);
    EditText totalOversEditText, totalPlayerEditText, teamOneEditText, teamTwoEditText;
    Button createMatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        totalOversEditText = findViewById(R.id.overEditText);
        totalPlayerEditText = findViewById(R.id.playersEditText);
        teamOneEditText = findViewById(R.id.teamOneEditText);
        teamTwoEditText = findViewById(R.id.teamTwoEditText);
        createMatchButton = findViewById(R.id.createMatchButton);

        createMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int overs = Integer.parseInt(totalOversEditText.getText().toString());
                int players = Integer.parseInt(totalPlayerEditText.getText().toString());
                String teamOne = teamOneEditText.getText().toString();
                String teamTwo = teamTwoEditText.getText().toString();

                matchReference.child("details").setValue(new Details(overs,players,teamOne,teamTwo));

                Intent intent = new Intent(MatchDetailsActivity.this, TeamActivity.class);
                startActivity(intent);

            }
        });



    }

}