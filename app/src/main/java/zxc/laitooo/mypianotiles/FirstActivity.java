package zxc.laitooo.mypianotiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    TextView score;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        score = (TextView)findViewById(R.id.high_score);
        start = (Button)findViewById(R.id.start_game);

        SharedPreferences preferences = getSharedPreferences("SCORE",MODE_PRIVATE);
        if (preferences.getInt("Score",9999) == 9999){
            score.setVisibility(View.GONE);
        }else {
            score.setText("High Score : " + String.valueOf(preferences.getInt("Score",9999)));
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("SCORE",MODE_PRIVATE);
        if (preferences.getInt("Score",9999) == 9999){
            score.setVisibility(View.GONE);
        }else {
            score.setText("High Score : " + String.valueOf(preferences.getInt("Score",9999)));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences preferences = getSharedPreferences("SCORE",MODE_PRIVATE);
        if (preferences.getInt("Score",9999) == 9999){
            score.setVisibility(View.GONE);
        }else {
            score.setText("High Score : " + String.valueOf(preferences.getInt("Score",9999)));
        }
    }
}
