package com.example.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private String topic;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Question> questionList = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer countDownTimer;

    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView timerTextView;
    private Button option1Button, option2Button, option3Button, option4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);

        // Retrieve the topic passed from HomeActivity
        topic = getIntent().getStringExtra("TOPIC");

        // Load questions based on the selected topic
        loadQuestions();

        // Set up click listeners for options
        setOptionListeners();
    }

    private void loadQuestions() {
        db.collection("questions")
                .whereEqualTo("topic", topic)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        for (DocumentSnapshot document : querySnapshot) {
                            Question question = document.toObject(Question.class);
                            questionList.add(question);
                            Log.d("QuizActivity", "Question: " + question.getQuestion());
                        }
                        startQuiz();
                    } else {
                        Toast.makeText(this, "No questions available for this topic.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to load questions.", Toast.LENGTH_SHORT).show();
                });
    }

    private void startQuiz() {
        if (!questionList.isEmpty()) {
            displayNextQuestion();
        }
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);

            // Update UI with the current question and options
            questionTextView.setText(currentQuestion.getQuestion());
            option1Button.setText(currentQuestion.getOptionA());
            option2Button.setText(currentQuestion.getOptionB());
            option3Button.setText(currentQuestion.getOptionC());
            option4Button.setText(currentQuestion.getOptionD());
            scoreTextView.setText("Score: " + score);

            // Start the timer for the current question
            startTimer();

            currentQuestionIndex++;
        } else {
            // End of quiz, navigate to score summary
            goToScoreSummary();
        }
    }

    private void startTimer() {
        // Cancel any existing timer before starting a new one
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(15000, 1000) {  // 15 seconds with 1-second interval
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time left: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                displayNextQuestion();  // Automatically move to the next question after time ends
            }
        }.start();
    }

    private void setOptionListeners() {
        Button[] options = {option1Button, option2Button, option3Button, option4Button};
        for (Button option : options) {
            option.setOnClickListener(view -> {
                // Check answer and update score if correct
                checkAnswer(((Button) view).getText().toString());
            });
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questionList.get(currentQuestionIndex - 1);
        if (currentQuestion.getCorrectOption().equals(selectedAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        scoreTextView.setText("Score: " + score);

        // Move to the next question after checking the answer
        displayNextQuestion();
    }

    private void goToScoreSummary() {
        Intent intent = new Intent(QuizActivity.this, ScoreSummaryActivity.class);
        intent.putExtra("FINAL_SCORE", score);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the timer to avoid memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
