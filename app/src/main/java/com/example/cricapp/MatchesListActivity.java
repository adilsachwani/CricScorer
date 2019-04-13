package com.example.cricapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapters.MatchAdapter;
import firebase_models.Details;

public class MatchesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Details> matchDetailsList;
    MatchAdapter matchAdapter;
    DatabaseReference matchesReference = FirebaseDatabase.getInstance().getReference().child("matches");
    TextView toolbarText;
    ImageView toolbarBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_list);

        setUpToolbar("Match Scores");

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        matchDetailsList = new ArrayList<>();

        getMatchesList();

    }

    private void getMatchesList() {

        matchesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot m : dataSnapshot.getChildren()){

                    Details d = m.child("details").getValue(Details.class);
                    matchDetailsList.add(d);

                }

                matchAdapter = new MatchAdapter(MatchesListActivity.this, matchDetailsList);
                recyclerView.setAdapter(matchAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
