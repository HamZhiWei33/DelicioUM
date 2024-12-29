//package com.example.sad;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class SignUpActivity extends AppCompatActivity {
//    EditText etUsername, etPassword, etConfirmPassword,etEmail;
//    Button btnSignUp;
//    DatabaseHelper db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.fragment_sign_up);
//
//        etUsername = findViewById(R.id.ETUsername);
//        etPassword = findViewById(R.id.ETPassword);
//        etEmail = findViewById(R.id.ETForgetEmail);
//        etConfirmPassword = findViewById(R.id.ETConfirmPassword);
//        btnSignUp = findViewById(R.id.BTNSignUp);
//        db = new DatabaseHelper(this);
//
//
//        TextView tvLogin = findViewById(R.id.TVLogin);
//        Button btnSignUp = findViewById(R.id.BTNSignUp);
//
//        tvLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Start LoginActivity
//                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = etUsername.getText().toString().trim();
//                String email = etEmail.getText().toString().trim();
//                String password = etPassword.getText().toString().trim();
//                String confirmPassword = etConfirmPassword.getText().toString().trim();
//
//                if (username.isEmpty() ||email.isEmpty() || password.isEmpty()||confirmPassword.isEmpty()) {
//                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    Toast.makeText(SignUpActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
//                } else if (db.isEmailExists(email)) {
//                    Toast.makeText(SignUpActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
//                } else if(!password.equals(confirmPassword)){
//                    Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
//                }else{
//                    // Proceed with registration
//                    boolean success = db.registerUser(username, email, password);
//                    if (success) {
//                        Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//                    } else {
//                        Toast.makeText(SignUpActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}

package com.example.sad;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class SignUpActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etConfirmPassword, etEmail;
    Button btnSignUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        // Initialize Views
        etUsername = findViewById(R.id.ETUsername);
        etPassword = findViewById(R.id.ETPassword);
        etEmail = findViewById(R.id.ETForgetEmail);
        etConfirmPassword = findViewById(R.id.ETConfirmPassword);
        btnSignUp = findViewById(R.id.BTNSignUp);
        db = new DatabaseHelper(this);

        TextView tvLogin = findViewById(R.id.TVLogin);

        // Handle Login Link
        tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnSignUp.setTextColor(Color.parseColor("#680303")); // Change to red when pressed
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                btnSignUp.setTextColor(Color.WHITE); // Revert to white when released
            }
            return false; // Ensure other events (like click) are still handled
        });

        // Handle Sign-Up Button
        btnSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(SignUpActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            } else if (db.isEmailExists(email)) {
                Toast.makeText(SignUpActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
            } else {
                // Proceed with registration
                boolean success = db.registerUser(username, email, password);
                if (success) {
                    Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(SignUpActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set initial drawable for password fields
        etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_password_hide, 0);
        etConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_password_hide, 0);

        // Toggle Password Visibility
        setupPasswordToggle(etPassword);
        setupPasswordToggle(etConfirmPassword);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupPasswordToggle(EditText editText) {
        editText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
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
