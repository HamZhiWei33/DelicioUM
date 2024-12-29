package com.example.sad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginEmail, etLoginPassword;
    Button btnLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        db = new DatabaseHelper(this);

        TextView tvForgetPassword = findViewById(R.id.TVForgetPassword);
        TextView tvSignUp = findViewById(R.id.TVSignUp);
        btnLogin = findViewById(R.id.BTNLogin);
        etLoginEmail = findViewById(R.id.ETForgetEmail);
        etLoginPassword = findViewById(R.id.ETPassword);

        // Redirect to ForgetPasswordActivity
        tvForgetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        // Redirect to SignUpActivity
        tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Handle Login Button
        btnLogin.setOnClickListener(view -> {
            String email = etLoginEmail.getText().toString().trim();
            String password = etLoginPassword.getText().toString().trim();
            if (db.checkUser(email, password)) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Redirect to another activity
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnLogin.setTextColor(Color.parseColor("#680303")); // Change to red when pressed
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                btnLogin.setTextColor(Color.WHITE); // Revert to white when released
            }
            return false; // Ensure other events (like click) are still handled
        });

        // Set initial drawable for password fields
        etLoginPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_password_hide, 0);

        // Toggle Password Visibility
        setupPasswordToggle(etLoginPassword);
    }

    // Setup method to toggle password visibility
    private void setupPasswordToggle(EditText editText) {
        editText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2; // Index for right drawable
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Check if the touch is on the right drawable
                if (event.getRawX() >= (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    // Toggle visibility
                    if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                        // Show password
                        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_password_toggle, 0);
                    } else {
                        // Hide password
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_password_hide, 0);
                    }
                    // Maintain cursor position
                    editText.setSelection(editText.getText().length());
                    return true;
                }
            }
            return false;
        });
    }
}
