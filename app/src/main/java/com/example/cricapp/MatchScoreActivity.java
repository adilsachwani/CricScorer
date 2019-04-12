package com.example.cricapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firebase_models.Score;

public class MatchScoreActivity extends AppCompatActivity {

    String match = "match1";

    DatabaseReference matchReference = FirebaseDatabase.getInstance().getReference().child(match);
    Button oneButton, twoButton, threeButton, fourButton, sixButton, outButton, wideButton, noBallButton;
    TextView scoreText, oversText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_score);

        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        fourButton = findViewById(R.id.fourButton);
        sixButton = findViewById(R.id.sixButton);
        outButton = findViewById(R.id.outButton);
        wideButton = findViewById(R.id.wideButton);
        noBallButton = findViewById(R.id.noBallButton);
        scoreText = findViewById(R.id.score);
        oversText = findViewById(R.id.overs);

        matchReference.child("score").setValue(new Score(0,0,0,0,0));

        addScore(oneButton);
        addScore(twoButton);
        addScore(threeButton);
        addScore(fourButton);
        addScore(sixButton);
        addScore(outButton);
        addScore(wideButton);
        addScore(noBallButton);

        updateScore();

    }

    private void addScore(final Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matchReference.child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Score s = dataSnapshot.getValue(Score.class);

                        int runs = s.getRuns();
                        int wkts = s.getWkts();
                        int balls = s.getBalls();
                        int wides = s.getWides();
                        int no_balls = s.getNoballs();

                        switch (button.getTag().toString()){
                            case "0": //wkts
                                wkts++;
                                balls++;
                                break;
                            case "1": //one run
                                runs++;
                                balls++;
                                break;
                            case "2": //two run
                                runs+=2;
                                balls++;
                                break;
                            case "3": //three run
                                runs+=3;
                                balls++;
                                break;
                            case "4": //four runs
                                runs+=4;
                                balls++;
                                break;
                            case "6": //six runs
                                runs+=6;
                                balls++;
                                break;
                            case "7": //wide ball
                                runs++;
                                wides++;
                            case "8": //no ball
                                runs++;
                                no_balls++;
                        }

                        dataSnapshot.getRef().setValue(new Score(balls,runs,wkts,wides,no_balls));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void updateScore(){

        matchReference.child("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Score s = dataSnapshot.getValue(Score.class);

                String score = s.getRuns() + "/" + s.getWkts();

                int over = s.getBalls() / 6;
                int balls = s.getBalls() % 6;

                String overs = over + "." + balls;

                scoreText.setText(score);
                oversText.setText(overs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}