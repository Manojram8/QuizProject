package com.example.quizproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up click listeners for each topic card
        findViewById(R.id.cardNature).setOnClickListener(v -> openQuiz("Nature"));
        findViewById(R.id.cardScience).setOnClickListener(v -> openQuiz("Science"));
        findViewById(R.id.cardComputerScience).setOnClickListener(v -> openQuiz("ComputerScience"));

        // Set up click listener for the History button
        findViewById(R.id.history_button).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
        });
    }

    private void openQuiz(String topic) {
        // Ensure the intent is correctly created and the topic is passed to the next activity
        Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
        intent.putExtra("TOPIC", topic); // Pass the selected topic to QuizActivity
        startActivity(intent);
    }
}
