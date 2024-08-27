package com.example.immune_keeper2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Openpage extends AppCompatActivity {

    private Button abt;
    private Button add;
    private Button log;

    RecyclerView recyclerView;
    ArrayList<String> name, phone;
    ConnectionHelper db;
    myadaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openpage);

        // Initialize EditText
        db = new ConnectionHelper(this);
        name = new ArrayList<>();
        phone = new ArrayList<>();
        recyclerView = findViewById(R.id.recview);
        adaptor = new myadaptor(this, name, phone);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displaydata();

        // Initialize buttons and image button
        abt = findViewById(R.id.abt);
        add = findViewById(R.id.add);
        log = findViewById(R.id.log);

        // Set onClickListeners for buttons
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Openpage.this, About.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Openpage.this, Addchild.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Openpage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displaydata() {
        Cursor cursor = db.readalldata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Clear the ArrayLists before adding new data
            name.clear();
            phone.clear();

            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                phone.add(cursor.getString(1));

                // Log each database entry
                String entry = "Name: " + cursor.getString(0) + ", Phone: " + cursor.getString(1);
                Log.d("Database", entry);
            }

            // Notify the adapter that the data set has changed
            adaptor.notifyDataSetChanged();
        }
    }
}
