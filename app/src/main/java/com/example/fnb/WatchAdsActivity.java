package com.example.fnb;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WatchAdsActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private TextView tvTotalAdsWatched, tvTotalEarnings;
    private Button btnWatchAd;
    private ProgressBar progressBar;

    private int adsWatchedCount = 0;
    private double adValue = 0.005;
    private double totalEarnings = 0.00;

    private RewardedAd rewardedAd;
    private String adUnitId = "ca-app-pub-8736804238249804/1428665845";

    private DatabaseReference databaseReference;
    private String currentUserEmail;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_ads);

        tvTotalAdsWatched = findViewById(R.id.tv_total_ads_watched);
        tvTotalEarnings = findViewById(R.id.tv_total_earnings);
        btnWatchAd = findViewById(R.id.btn_watch_ad);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        // Get current user ID (can be obtained after user login/authentication)
        currentUserId = "user123";  // Replace with actual logic to get user ID

        // Load user data from Firebase
        loadUserData();

        // Initialize MobileAds SDK
        MobileAds.initialize(this, initializationStatus -> {});

        // Load a rewarded ad
        loadRewardedAd();

        btnWatchAd.setOnClickListener(v -> {
            if (rewardedAd != null) {
                showRewardedAd();
            } else {
                loadRewardedAd();
            }
        });
    }

    // Method to load user data from Firebase
    private void loadUserData() {
        databaseReference.child(currentUserId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                Map<String, Object> data = (Map<String, Object>) task.getResult().getValue();
                if (data != null) {
                    adsWatchedCount = ((Long) data.get("adsWatchedCount")).intValue();
                    totalEarnings = (double) data.get("totalEarnings");

                    // Update UI
                    tvTotalAdsWatched.setText("Total Ads Watched: " + adsWatchedCount);
                    tvTotalEarnings.setText(String.format("Total Earnings: $%.3f", totalEarnings));
                }
            }
        });
    }

    // Method to load a rewarded ad
    private void loadRewardedAd() {
        progressBar.setVisibility(View.VISIBLE);

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, adUnitId, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd ad) {
                rewardedAd = ad;
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                progressBar.setVisibility(View.GONE);
                rewardedAd = null;
            }
        });
    }

    // Method to show a rewarded ad
    private void showRewardedAd() {
        if (rewardedAd != null) {
            rewardedAd.show(this, rewardItem -> {
                // Update ads watched and earnings
                adsWatchedCount++;
                totalEarnings += adValue;

                // Update UI
                tvTotalAdsWatched.setText("Total Ads Watched: " + adsWatchedCount);
                tvTotalEarnings.setText(String.format("Total Earnings: R%.3f", totalEarnings));

                // Save data to Firebase
                saveUserData();

                // Load the next ad
                loadRewardedAd();
            });

            rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    rewardedAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    rewardedAd = null;
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    rewardedAd = null;
                }
            });
        }
    }

    // Method to save user data to Firebase
    private void saveUserData() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("adsWatchedCount", adsWatchedCount);
        userData.put("totalEarnings", totalEarnings);

        // Save the updated user data to the database
        databaseReference.child(currentUserId).setValue(userData);
    }
}
