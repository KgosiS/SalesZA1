package com.example.fnb;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordctivity extends AppCompatActivity {

    private EditText emailField;
    private Button resetPasswordButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_passwordctivity);

        emailField = findViewById(R.id.forgot_email);
        resetPasswordButton = findViewById(R.id.reset_password_button);

        mAuth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        String email = emailField.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(ForgotPasswordctivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordctivity.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                        finish();  // Optionally redirect to the Sign In page
                    } else {
                        Toast.makeText(ForgotPasswordctivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}