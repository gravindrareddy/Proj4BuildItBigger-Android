package udacity.redgun.jokeconsumerandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Bundle extras = getIntent().getExtras();
        String joke;

        if (extras != null) {
            joke = extras.getString("joke");
            ((TextView) findViewById(R.id.joke_android_lib_tv)).setText(joke);
        }
    }
}
