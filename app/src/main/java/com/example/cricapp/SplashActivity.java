package com.example.cricapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    Animation splashAnim;
    final int SPLASH_TIME = 3000;
    TextView mainTextView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        mainTextView = findViewById(R.id.mainText);
        splashAnim = AnimationUtils.loadAnimation(this,R.anim.splash_anim);
        mainTextView.startAnimation(splashAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(firebaseUser == null){
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
                else{
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                }


                finish();
            }
        },SPLASH_TIME);

    }
}