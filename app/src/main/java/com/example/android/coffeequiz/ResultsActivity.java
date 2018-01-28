package com.example.android.coffeequiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    String inName;
    String inScore;
    int newScore;
    String finalScoreMessage;
    TextView congratsMessage;
    Button shareResults;
    Button startMainActivity;
    String shareMessageBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView name = findViewById(R.id.results_name);
        TextView finalScore = findViewById(R.id.results_score);
// takes the values of @userName and @score again into this activity
        Intent i = getIntent();
        this.inName = i.getStringExtra("outName");
        name.setText(inName);

        this.inScore = i.getStringExtra("outScore");
        newScore = Integer.parseInt(inScore);
        //creates a message to congratulate the user based on the number of correct answers
        congratsMessage = findViewById(R.id.congrats_message);
        if (newScore <= 2) {
            congratsMessage.setText(R.string.do_better_message);
        }
        if (newScore >= 3) {
            congratsMessage.setText(R.string.congrats_message);
        }
        finalScoreMessage = getString(R.string.answered_correct) + newScore + getString(R.string.out_of_4);


        finalScore.setText(finalScoreMessage);

    }
//creates an intent for the Try Again button, that takes you back to the Main Activity
    public void startMainActivity(View view) {
        Intent startMain = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(startMain);
    }
//creates an intent for the Share Results button, which takes you to an email app to share your results with your friends
    public void shareResults(View view) {
        String congrMessage = congratsMessage.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SENDTO);
        shareIntent.setType("text");
        shareIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        shareMessageBody = getString(R.string.results_message_body);
        shareMessageBody += "\n" + inName;
        shareMessageBody += "\n" + finalScoreMessage;
        shareMessageBody += "\n" + congrMessage;
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessageBody);
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);

        }
    }
}

