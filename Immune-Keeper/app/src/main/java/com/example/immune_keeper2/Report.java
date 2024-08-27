package com.example.immune_keeper2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Get the data passed from the adapter
        Intent intent = getIntent();
        String childName = intent.getStringExtra("childName");
        String childPhone = intent.getStringExtra("childPhone");

        // Find the TextViews in your layout
        TextView greetingTextView = findViewById(R.id.greetingText);
        TextView childIdTextView = findViewById(R.id.childIdText);

        // Set the text for the greeting and child ID TextViews
        greetingTextView.setText("HELLO, " + childName);
        childIdTextView.setText("ID: " + childPhone);

        // Add checkboxes dynamically
        LinearLayout checkboxContainer = findViewById(R.id.checkboxContainer);
        for (int i = 0; i < 5; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText("Checkbox " + (i + 1));
            checkboxContainer.addView(checkBox);
        }
    }
}
