package com.example.immune_keeper2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Addchild extends AppCompatActivity {
    EditText uname, father, mother, dob, phone;
    ImageButton add;
    ConnectionHelper db;

    // Define a constant for the permission request
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addchild);
        uname = findViewById(R.id.name);
        father = findViewById(R.id.fname);
        mother = findViewById(R.id.mname);
        dob = findViewById(R.id.dob);
        phone = findViewById(R.id.phnum);
        add = findViewById(R.id.addbtn);

        db = new ConnectionHelper(Addchild.this);

        // Check for SMS permission when activity is created
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname = uname.getText().toString(); // Fetch child's name
                String faname = father.getText().toString(); // Fetch father's name
                String moname = mother.getText().toString(); // Fetch mother's name
                String dobb = dob.getText().toString(); // Fetch date of birth
                String mobile = phone.getText().toString(); // Fetch mobile number

                // Proceed with sending SMS if permission is granted
                if (ContextCompat.checkSelfPermission(Addchild.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    long recordid = db.saveNewUserData(pname, faname, moname, dobb, mobile, Addchild.this);

                    if (recordid > 0) {
                        Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                        // If the data is saved successfully, start the Report activity
                        Intent intent = new Intent(Addchild.this, Report.class);
                        // Pass the child's name and phone number to the Report activity
                        intent.putExtra("childName", pname);
                        intent.putExtra("childPhone", mobile);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Saved Failed", Toast.LENGTH_LONG).show();
                    }

                    Intent intent = new Intent(Addchild.this, Openpage.class);
                    startActivity(intent);
                } else {
                    // Permission denied, show a message or take appropriate action
                    Toast.makeText(Addchild.this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted to send SMS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
