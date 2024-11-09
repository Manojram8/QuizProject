public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up click listeners for topic cards
        findViewById(R.id.cardNature).setOnClickListener(v -> openQuiz("Nature"));
        findViewById(R.id.cardScience).setOnClickListener(v -> openQuiz("Science"));
        findViewById(R.id.cardComputerScience).setOnClickListener(v -> openQuiz("Computer Science"));

        findViewById(R.id.history_button).setOnClickListener(v -> {
            startActivity(new Intent(this, HistoryActivity.class));
        });
    }

    private void openQuiz(String topic) {
        Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
