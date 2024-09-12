package com.example.fnb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class VouchersActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private TextView tvAvailablePoints, tvScreenshotPath;
    private Spinner spinnerVoucher;
    private Button btnUploadScreenshot, btnSubmitClaim;
    private ListView lvClaimedVouchers;

    private int availablePoints = 15000;  // Example available points
    private ArrayList<String> claimedVouchers;
    private ArrayAdapter<String> claimedVouchersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers);

        tvAvailablePoints = findViewById(R.id.tv_available_points);
        spinnerVoucher = findViewById(R.id.spinner_voucher);
        tvScreenshotPath = findViewById(R.id.tv_screenshot_path);
        btnUploadScreenshot = findViewById(R.id.btn_upload_screenshot);
        btnSubmitClaim = findViewById(R.id.btn_submit_claim);
        lvClaimedVouchers = findViewById(R.id.lv_claimed_vouchers);

        // Set available points
        tvAvailablePoints.setText("Available Points: " + availablePoints);

        // Initialize claimed vouchers list and adapter
        claimedVouchers = new ArrayList<>();
        claimedVouchersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, claimedVouchers);
        lvClaimedVouchers.setAdapter(claimedVouchersAdapter);

        // Upload screenshot button logic
        btnUploadScreenshot.setOnClickListener(v -> openFileChooser());

        // Submit claim button logic
        btnSubmitClaim.setOnClickListener(v -> {
            String selectedVoucher = spinnerVoucher.getSelectedItem().toString();

            // Check if user has enough points
            if (canClaimVoucher(selectedVoucher)) {
                // Deduct points and add to claimed vouchers
                deductPoints(selectedVoucher);
                claimedVouchers.add(selectedVoucher);
                claimedVouchersAdapter.notifyDataSetChanged();
            } else {
                // Show error message (use a Toast, Snackbar, or dialog)
            }
        });
    }

    // Method to open file chooser for screenshot
    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            tvScreenshotPath.setText(imageUri.getPath());
        }
    }

    // Method to check if the user can claim the selected voucher
    private boolean canClaimVoucher(String voucher) {
        int voucherPoints = getPointsFromVoucher(voucher);
        return availablePoints >= voucherPoints;
    }

    // Method to deduct points after claiming a voucher
    private void deductPoints(String voucher) {
        int voucherPoints = getPointsFromVoucher(voucher);
        availablePoints -= voucherPoints;
        tvAvailablePoints.setText("Available Points: " + availablePoints);
    }

    // Helper method to extract points from voucher string
    private int getPointsFromVoucher(String voucher) {
        if (voucher.contains("10000")) return 10000;
        if (voucher.contains("20000")) return 20000;
        if (voucher.contains("30000")) return 30000;
        if (voucher.contains("40000")) return 40000;
        if (voucher.contains("50000")) return 50000;
        return 0;
    }
}
