package app.com.cricapp.prashantmaurya.scoremonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);

        TextView matchBetween=(TextView)findViewById(R.id.match_between_detail);
        Bundle bundle=getIntent().getExtras();
        String matchBetweens=bundle.getString("MatchBetween");
        matchBetween.setText(matchBetweens);



    }
}
