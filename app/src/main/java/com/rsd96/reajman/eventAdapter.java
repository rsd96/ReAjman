package com.rsd96.reajman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arshadfarooq on 1/26/18.
 */

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.eventViewHolder> {

    ArrayList<Event> events = new ArrayList<>();
    Context context;

    public eventAdapter(ArrayList<Event> events, Context context ){
        this.events = events;
        this.context = context;
    }

    @Override
    public eventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment, parent, false);
        eventViewHolder eventViewHolder = new eventViewHolder(view, this.context, events);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(eventViewHolder holder, int position) {

        Event event = events.get(position);

        holder.eventImage.setImageResource(event.getImageID());
        holder.eText.setText(event.geteName());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class eventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView eventImage;
        TextView eText;
        ArrayList<Event> events = new ArrayList<>();
        Context context;

        public eventViewHolder(View view, Context context, ArrayList<Event> events){

            super(view);
            this.context = context;
            this.events = events;
            view.setOnClickListener(this );
            eventImage = view.findViewById(R.id.eventImage);
            eText = view.findViewById(R.id.eText);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Event event = this.events.get(position);

            Intent intent = new Intent(this.context, EventDetails.class);
            intent.putExtra("imageID", event.getImageID());
            intent.putExtra("DText", event.geteName());
            this.context.startActivity(intent);

        }
    }
}
