package com.rsd96.reajman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetails extends AppCompatActivity {

    ImageView imageView;
    TextView dText;
    String [] name, paragraph;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        imageView = (ImageView) findViewById(R.id.DImage);
        dText = (TextView) findViewById(R.id.DText);

        text = getIntent().getStringExtra("DText");

        name = getResources().getStringArray(R.array.event_names);
        paragraph = getResources().getStringArray(R.array.paragraph);

        int counter = 0;

        for (String Name : name){

            if(text.equals(Name))
                break;
            else
                counter++;

        }

        imageView.setImageResource(getIntent().getIntExtra("imageID",00));
        dText.setText(paragraph[counter]);
    }

    protected void buttonClick(View view){
        Toast.makeText(EventDetails.this, "A user Joined!", Toast.LENGTH_LONG).show();
    }
}
