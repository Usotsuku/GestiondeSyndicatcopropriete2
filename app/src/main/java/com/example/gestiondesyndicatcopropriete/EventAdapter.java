package com.example.gestiondesyndicatcopropriete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<Map.Entry<String, String>> eventsList;
    private final SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMd", Locale.getDefault()); // Changed format to 'yyyyMd'

    public EventAdapter(List<Map.Entry<String, String>> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Map.Entry<String, String> event = eventsList.get(position);
        String dateString = event.getKey();
        String eventDescription = event.getValue();

        try {
            holder.dateTextView.setText(displayFormat.format(dateFormat.parse(dateString)));
        } catch (ParseException e) {
            holder.dateTextView.setText(dateString); // fallback
        }
        holder.eventTextView.setText(eventDescription);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView;
        TextView eventTextView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            eventTextView = itemView.findViewById(R.id.eventTextView);
        }
    }
}
