package com.example.quizproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class ScoreSummaryActivity extends AppCompatActivity {

    private TextView finalScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);

        finalScoreTextView = findViewById(R.id.finalScoreTextView);

        // Retrieve the score from the Intent
        int finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);

        // Display the score
        finalScoreTextView.setText("Your Final Score: " + finalScore);
    }
}
