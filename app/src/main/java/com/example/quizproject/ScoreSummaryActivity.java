package com.example.quizproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);

        // Retrieve the score from the intent
        int score = getIntent().getIntExtra("FINAL_SCORE", 0);

        // Display the score
        TextView scoreTextView = findViewById(R.id.finalScoreTextView);
        scoreTextView.setText("Your Final Score: " + score);
    }
}
