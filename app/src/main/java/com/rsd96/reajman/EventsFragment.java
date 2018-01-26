package com.rsd96.reajman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ramshad on 1/26/18.
 */

public class EventsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Event> list = new ArrayList<>();

    int [] image_ID = {R.drawable.test, R.drawable.test1, R.drawable.test2, R.drawable.test3, R.drawable.test4};
    String [] name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        int count = 0;

        name = getResources().getStringArray(R.array.event_names);

        for (String Name : name){

            Event event = new Event(image_ID[count], Name);
            count++;
            list.add(event);
        }

        recyclerView = view.findViewById(R.id.rView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new eventAdapter(list, getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);


    }


}
