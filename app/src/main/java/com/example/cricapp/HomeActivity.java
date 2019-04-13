package com.example.cricapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    LinearLayout createMatchButton, matchScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createMatchButton = findViewById(R.id.createMatchButton);
        matchScoreButton = findViewById(R.id.matchScoresButton);

        createMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MatchDetailsActivity.class);
                startActivity(intent);
            }
        });

        matchScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MatchesListActivity.class);
                startActivity(intent);
            }
        });

    }
}
