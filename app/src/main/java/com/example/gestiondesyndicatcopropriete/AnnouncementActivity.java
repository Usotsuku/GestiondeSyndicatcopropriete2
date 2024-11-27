package com.example.gestiondesyndicatcopropriete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AnnouncementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Map.Entry<String, String>> eventsList;
    private DatabaseReference databaseReference;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventsList = new ArrayList<>();
        eventAdapter = new EventAdapter(eventsList);
        recyclerView.setAdapter(eventAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");
        dateFormat = new SimpleDateFormat("yyyyMd", Locale.getDefault()); // "yyyymd format lowla is not working

        loadUpcomingEvents();
    }

    private void loadUpcomingEvents() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventsList.clear();
                Calendar today = Calendar.getInstance();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String dateString = dataSnapshot.getKey();
                    String eventDescription = dataSnapshot.getValue(String.class);

                    try {
                        Date eventDate = dateFormat.parse(dateString);
                        if (eventDate != null && eventDate.after(today.getTime())) {
                            eventsList.add(new AbstractMap.SimpleEntry<>(dateString, eventDescription));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
