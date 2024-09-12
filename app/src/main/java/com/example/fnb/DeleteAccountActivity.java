package com.example.fnb;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteAccountActivity extends AppCompatActivity {

    private EditText passwordField;
    private Button deleteAccountButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        passwordField = findViewById(R.id.delete_password);
        deleteAccountButton = findViewById(R.id.delete_account_button);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        deleteAccountButton.setOnClickListener(v -> deleteUserAccount());
    }

    private void deleteUserAccount() {
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(DeleteAccountActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentUser != null) {
            // Re-authenticate the user before deleting the account
            AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), password);
            currentUser.reauthenticate(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    currentUser.delete().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            Toast.makeText(DeleteAccountActivity.this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
                            // Redirect to the sign-in page or main activity
                            finish();
                        } else {
                            Toast.makeText(DeleteAccountActivity.this, "Error: " + deleteTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(DeleteAccountActivity.this, "Re-authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}