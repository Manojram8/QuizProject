import static androidx.core.content.ContextCompat.startActivity;

public class FlashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FlashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}