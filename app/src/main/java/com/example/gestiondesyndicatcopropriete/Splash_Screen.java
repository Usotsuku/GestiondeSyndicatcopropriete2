package com.example.gestiondesyndicatcopropriete;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash_Screen extends AppCompatActivity {

    Animation anim;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        anim = AnimationUtils.loadAnimation(Splash_Screen.this,R.anim.anim);
        logo = findViewById(R.id.logo);
        logo.startAnimation(anim);

        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}