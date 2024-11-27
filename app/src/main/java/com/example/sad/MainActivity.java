package com.example.sad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.BTNLogin);
        Button signupBtn = findViewById(R.id.BTNSignup);

        // Change text color and navigate to LoginActivity
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loginBtn.setTextColor(Color.parseColor("#680303"));  // Set pressed text color
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                loginBtn.setTextColor(Color.parseColor("#680303")); // Change to red when pressed
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                loginBtn.setTextColor(Color.WHITE); // Revert to white when released
            }
            return false; // Ensure other events (like click) are still handled
        });

        // Change text color and navigate to SignupActivity
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signupBtn.setTextColor(Color.parseColor("#680303"));  // Set pressed text color
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                signupBtn.setTextColor(Color.parseColor("#680303")); // Change to red when pressed
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                signupBtn.setTextColor(Color.WHITE); // Revert to white when released
            }
            return false; // Ensure other events (like click) are still handled
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}