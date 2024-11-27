package com.example.gestiondesyndicatcopropriete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlanningActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editText;

    private String stringDateSelected;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planning);

        calendarView = findViewById(R.id.calendarView);
        editText = findViewById(R.id.eventText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                stringDateSelected = Integer.toString(year)+Integer.toString(month+1)+Integer.toString(dayOfMonth);
                calendarClicked();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");
    }

    private void calendarClicked(){

        databaseReference.child(stringDateSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null){
                    editText.setText(snapshot.getValue().toString());
                }else{
                    editText.setText("No Event");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void buttonSaveEvent(View view){
        String eventName = editText.getText().toString().trim();
        if (!eventName.isEmpty()) {
            databaseReference.child(stringDateSelected).setValue(eventName);
            Toast.makeText(this, "Event saved successfully", Toast.LENGTH_SHORT).show();
        }
    }
}