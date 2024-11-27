package com.example.gestiondesyndicatcopropriete;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    LinearLayout msglinearLayout;
    LinearLayout proximite;
    LinearLayout planning;
    LinearLayout announcement;


    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        msglinearLayout = findViewById(R.id.messageLinearLayout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        proximite = findViewById(R.id.proximite);
        planning = findViewById(R.id.planning);
        announcement = findViewById(R.id.announcement);


        String displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        TextView usernameText = findViewById(R.id.username);
        usernameText.setText("Mr/Mme " + displayName);

        msglinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BeginChatActivity.class));
            }
        });

        proximite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });

        planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlanningActivity.class));
            }
        });

        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnnouncementActivity.class));
            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
            return true;
        }


        return false;
    }
}