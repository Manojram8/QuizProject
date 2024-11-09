package com.example.quizproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialize the TextView
        historyTextView = findViewById(R.id.historyTextView);

        // Fetch history data from Firestore
        fetchHistoryData();
    }

    private void fetchHistoryData() {
        db.collection("quizHistory") // Change "history" to your Firestore collection name
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    StringBuilder historyData = new StringBuilder();

                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                        for (DocumentSnapshot document : querySnapshot) {
                            // Get data from each document
                            String question = document.getString("question"); // Change field names accordingly
                            String answer = document.getString("answer");

                            // Append the data to the StringBuilder
                            historyData.append("Q: ").append(question).append("\n")
                                    .append("A: ").append(answer).append("\n\n");
                        }

                        // Set the data to the TextView
                        if (historyData.length() > 0) {
                            historyTextView.setText(historyData.toString());
                        } else {
                            historyTextView.setText("No data available.");
                        }
                    } else {
                        historyTextView.setText("No data available.");
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                    Toast.makeText(HistoryActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                });
    }
}

