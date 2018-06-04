package com.aslearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by vancoul on 6/3/18.
 */

public class Quiz extends AppCompatActivity {
    Queue<Question> questions;
    private Question currQuestion;
    private DatabaseAccess dbAccess;
    private String chosenAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String lesson = intent.getStringExtra("lessonName");
        dbAccess = DatabaseAccess.getInstance(this);
        questions = new LinkedList<Question>(dbAccess.selectQuestionsByLesson(lesson));
        currQuestion = questions.remove();
        setupQuestion();
    }

    private void setupQuestion() {
        switch(currQuestion.getType()) {
            case "sign2eng":
                setContentView(R.layout.multiple_choice);
                makeSign2EngQuestion();
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

    private void makeSign2EngQuestion(){
        ImageView signImg = findViewById(R.id.signImage);
        VideoView signVid = findViewById(R.id.signVideo);
        Button[] buttons = new Button[4];
        buttons[0] = (Button)findViewById(R.id.MCFirstGuess);
        buttons[1] = (Button)findViewById(R.id.MCSecondGuess);
        buttons[2] = (Button)findViewById(R.id.MCThirdGuess);
        buttons[3] = (Button)findViewById(R.id.MCFourthGuess);
        Button confirmButton = findViewById(R.id.MCConfirmButton);

        int correctIndex = new Random().nextInt(4);
        ArrayList<String> answerList = currQuestion.getWrongAnswersAsList();
        answerList.add(correctIndex, currQuestion.getAnswer());

        for(int i=0; i<4; i++){
            Button currButton = buttons[i];
            currButton.setText(answerList.get(i));
            //currButton.setOnClickListener();
        }
    }

    public void MCAnswer(View view){
        chosenAnswer = ((Button)view).getText().toString();
    }
}
