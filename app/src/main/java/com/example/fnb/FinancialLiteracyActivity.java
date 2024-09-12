package com.example.fnb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinancialLiteracyActivity extends AppCompatActivity {

    // Declare views
    private TextView headerTitle, definitionsTitle, tipsTitle, examplesTitle;
    private ScrollView definitionsScrollView, tipsScrollView, examplesScrollView;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_literacy); // Use the provided XML layout

        // Initialize views
        headerTitle = findViewById(R.id.header_title);
        definitionsTitle = findViewById(R.id.definitions_title);
        tipsTitle = findViewById(R.id.tips_title);
        examplesTitle = findViewById(R.id.examples_title);
        definitionsScrollView = findViewById(R.id.definitions_scroll_view);
        tipsScrollView = findViewById(R.id.tips_scroll_view);
        examplesScrollView = findViewById(R.id.examples_scroll_view);
        startQuizButton = findViewById(R.id.start_quiz_button);

        // Add click listener for the "Start Quiz" button
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to QuizActivity when the button is clicked
                Intent intent = new Intent(FinancialLiteracyActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
