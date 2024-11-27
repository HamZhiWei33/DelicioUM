package com.example.sad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etNewPassword, etConfirmPassword;
    DatabaseHelper db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_reset_password);
        etNewPassword = findViewById(R.id.ETNewPassword);
        etConfirmPassword = findViewById(R.id.ETConfirmPassword);
        Button btnUpdatePassword = findViewById(R.id.BTNUpdatePassword);
        db = new DatabaseHelper(this);

        email = getIntent().getStringExtra("email");

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = db.updatePassword(email, newPassword);
                    if (success) {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                    }
                }
                // Start LoginActivity
//                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
//                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}