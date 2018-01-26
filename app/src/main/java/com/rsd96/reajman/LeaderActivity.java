package com.rsd96.reajman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ramshad on 1/26/18.
 */

public class LeaderActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        // int i=0;
        ListView list = (ListView) findViewById(R.id.sidlist);
        Person z = new Person("Ahmed", "10");
        Person a = new Person("sidra", "780");
        Person b = new Person("shahlin", "100");
        Person c = new Person("mehvish", "90");
        Person d = new Person("periperi", "30");

        ArrayList<Person> personlist = new ArrayList<Person>();
        personlist.add(a);
        personlist.add(b);
        personlist.add(c);
        personlist.add(d);
        personlist.add(z);
        PersonAdapter adapter = new PersonAdapter(this, personlist);
        list.setAdapter(adapter);
    }


}
