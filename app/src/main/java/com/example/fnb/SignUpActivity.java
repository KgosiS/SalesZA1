package com.example.fnb;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameField, surnameField, emailField, passwordField;
    private Button signUpButton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        nameField = findViewById(R.id.signup_name);
        surnameField = findViewById(R.id.signup_surname);
        emailField = findViewById(R.id.signup_email);
        passwordField = findViewById(R.id.signup_password);
        signUpButton = findViewById(R.id.signup_button);
        TextView signinText = findViewById(R.id.text_signin);

        signinText.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        });

        // Set up the sign-up button click listener
        signUpButton.setOnClickListener(v -> {
            // Retrieve input from the form fields
            String firstName = nameField.getText().toString().trim();
            String lastName = surnameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            // Validate input
            if (TextUtils.isEmpty(firstName)) {
                nameField.setError("First name is required");
                return;
            }
            if (TextUtils.isEmpty(lastName)) {
                surnameField.setError("Surname is required");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                emailField.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passwordField.setError("Password is required");
                return;
            }
            if (password.length() < 6) {
                passwordField.setError("Password must be at least 6 characters long");
                return;
            }

            // Register the user with Firebase Authentication
            signUpUser(email, password);
        });
    }

    // Method to register the user and store their data in SQLite
    private void signUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign-up success, navigate to MainActivity
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign-up fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
