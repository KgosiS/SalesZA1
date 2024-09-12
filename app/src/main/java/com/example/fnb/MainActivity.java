package com.example.fnb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnSales, btnFinance, btnTips, btnWatchAds, btnVouchers;
    private TextView welcomeMessage;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {
            // SDK initialized successfully
        });

        // Initialize views
        welcomeMessage = findViewById(R.id.welcome_message);
        btnSales = findViewById(R.id.button3);
        btnFinance = findViewById(R.id.button4);
        btnTips = findViewById(R.id.button1);
        btnWatchAds = findViewById(R.id.button2);
        btnVouchers = findViewById(R.id.button5);

        // Set onClickListeners for each button
        btnSales.setOnClickListener(v -> openSalesPage());
        btnFinance.setOnClickListener(v -> openFinancePage());
        btnTips.setOnClickListener(v -> openTipsPage());
        btnWatchAds.setOnClickListener(v -> openWatchAdsPage());
        btnVouchers.setOnClickListener(v -> openVouchersPage());

        // Check login status using SharedPreferences
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // No user is logged in, redirect to SignUpActivity or LoginActivity
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            finish(); // Finish MainActivity to prevent the user from returning with back button
        } else {
            // User is signed in, update UI accordingly
            welcomeMessage.setText("Welcome " + currentUser.getEmail());
        }
    }


    // Method to open Sales Page
    private void openSalesPage() {
        Intent intent = new Intent(this, SalesActivity.class);
        startActivity(intent);
    }

    // Method to open Financial Literacy Page
    private void openFinancePage() {
        Intent intent = new Intent(this, FinancialLiteracyActivity.class);
        startActivity(intent);
    }

    // Method to open Tips Page
    private void openTipsPage() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }

    // Method to open Watch Ads Page
    private void openWatchAdsPage() {
        Intent intent = new Intent(this, WatchAdsActivity.class);
        startActivity(intent);
    }

    // Method to open Vouchers Page
    private void openVouchersPage() {
        Intent intent = new Intent(this, VouchersActivity.class);
        startActivity(intent);
    }
}
