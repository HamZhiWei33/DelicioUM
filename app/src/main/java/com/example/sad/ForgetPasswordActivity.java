package com.example.sad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText etEmail;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (R.layout.fragment_forget_password != 0) {
            setContentView(R.layout.fragment_forget_password);
        } else {
            throw new IllegalStateException("Layout resource missing for fragment_forget_password");
        }

        TextView tvLogIn = findViewById(R.id.TVLogIn);
        Button btnResetPassword = findViewById(R.id.BTNResetPassword);
        etEmail = findViewById(R.id.ETForgetEmail);
        db = new DatabaseHelper(this);

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                if (email == null || email.isEmpty()) {
                    Toast.makeText(ForgetPasswordActivity.this, "No email provided", Toast.LENGTH_SHORT).show();
                } else if (!db.isEmailExists(email)) {
                    Toast.makeText(ForgetPasswordActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });
    }
}