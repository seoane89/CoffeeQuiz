package com.example.android.coffeequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //initial value of the points
    int score = 0;
    RadioGroup question1, question2;
    EditText userNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        ActionBar actionBar = getSupportActionBar();
        actionBar.hide()*/
        ;
        setContentView(R.layout.activity_main);

        question1 = findViewById(R.id.question_1_group);
        question2 = findViewById(R.id.question_2_group);

    }


    public void calculatePoints() {
        //checks if the user answered correct the first question
        if (question1.getCheckedRadioButtonId() == R.id.question_1a) {
            score = score + 1;
        }

        //checks if the user answered correct the second question
        if (question2.getCheckedRadioButtonId() == R.id.question_2c) {
            score = score + 1;
        }

        //checks if the user answered correct the third question
        EditText inputCoffeeType = (EditText) findViewById(R.id.input_coffee_type);
        String inputCoffee = inputCoffeeType.getText().toString();
        if (inputCoffee.equals("Robusta")) {
            score = score + 1;
        }

        //checks if the user answered correct the fourth question:

        //checks if the user checked Espresso, and stores the result in a boolean
        CheckBox checkBoxEspresso = (CheckBox) findViewById(R.id.checkbox_espresso);
        boolean hasEspresso = checkBoxEspresso.isChecked();

        //checks if the user checked Milk, and stores the result in a boolean
        CheckBox checkBoxMilk = (CheckBox) findViewById(R.id.checkbox_milk);
        boolean hasMilk = checkBoxMilk.isChecked();

        //checks if the user checked Milk Foam, and stores the result in a boolean
        CheckBox checkBoxMilkFoam = (CheckBox) findViewById(R.id.checkbox_milk_foam);
        boolean hasMilkFoam = checkBoxMilkFoam.isChecked();

        if (hasEspresso && hasMilk && hasMilkFoam) {
            score = score + 1;
        }
    }

    public void submitResults(View view) {
        calculatePoints();
        userNameInput = findViewById(R.id.name_input);
        String userName = userNameInput.getText().toString();
        String scoreSummary = createScoreSummary(userName, score);
        Toast.makeText(getApplicationContext(), scoreSummary,
                Toast.LENGTH_LONG).show();
        score = 0;


    }

    public String createScoreSummary(String userName, int score) {
        String scoreSummary = userName + "\n" + getString(R.string.your_score_is) + " " + score + " " + getString(R.string.out_of_4);
        return scoreSummary;

    }


}



