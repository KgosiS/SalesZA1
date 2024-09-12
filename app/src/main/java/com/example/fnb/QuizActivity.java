package com.example.fnb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private RadioButton option1, option2, option3, option4;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            if (option1.isChecked()) {
                Toast.makeText(this, "Correct! A budget is a plan for managing income and expenses.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect. The correct answer is: A budget is a plan for managing income and expenses.", Toast.LENGTH_LONG).show();
            }
        });
    }
}