package com.aslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aslearn.db.Question;

import java.util.ArrayList;

/**
 * Created by vancoul on 6/3/18.
 */

public class Quiz extends AppCompatActivity {
    ArrayList<Question> questions;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setupQuestion() {
        switch(question.getType()) {
            case "sign2eng":
              //  setContentView(R.layout.multiple_choice);
                break;
            case "eng2sign":
                //TODO make multiple choice
              //  setContentView(R.layout.multiple_choice);
                break;
            case "textEntry":
              //  setContentView(R.layout.fingerspelling_questions);
                break;
            case "matching":
                //TODO make matching
        //        setContentView(R.layout.matching);
                break;
        }

    }
}
