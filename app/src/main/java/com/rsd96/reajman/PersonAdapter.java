package com.rsd96.reajman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ramshad on 1/26/18.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, ArrayList<Person> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leader_content, parent, false);
        }
        // Lookup view for data population
        ImageView badge = convertView.findViewById(R.id.iv_leader);
        TextView tvpername = (TextView) convertView.findViewById(R.id.tv_content);
        TextView tvperid = (TextView) convertView.findViewById(R.id.tv_contenttwo);
        // Populate the data into the template view using the data object
        tvpername.setText(person.name);
        tvperid.setText(person.id);
        if(position == 0)
            badge.setVisibility(View.VISIBLE);
        else
            badge.setVisibility(View.GONE);
        // Return the completed view to render on screen
        return convertView;
    }
}