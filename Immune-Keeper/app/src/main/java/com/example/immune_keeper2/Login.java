package com.example.immune_keeper2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    // Define correct username and password
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Find the login button in your layout
        Button loginButton = findViewById(R.id.login);

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Find username and password EditText fields
                EditText usernameEditText = findViewById(R.id.editTextText2);
                EditText passwordEditText = findViewById(R.id.editTextTextPassword);

                // Get the entered username and password
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                // Check if username and password are incorrect
                if (!enteredUsername.equals(CORRECT_USERNAME) || !enteredPassword.equals(CORRECT_PASSWORD)) {
                    // If both username and password are incorrect, show a toast message indicating invalid credentials
                    Toast.makeText(Login.this, "Login Credentials Invalid!!", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }

                // If both username and password are correct, navigate to Mainpage activity
                Intent intent = new Intent(Login.this, Openpage.class);
                startActivity(intent);
            }
        });
    }
}
