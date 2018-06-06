package com.aslearn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Question;
import com.aslearn.db.Word;

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
        nextQuestion();
    }

    private void nextQuestion(){
        //End quiz if there are no more questions
        chosenAnswer = "";
        if (questions.isEmpty()){
            System.out.println("No more questions");
            dbAccess.updateFinishedLesson(currQuestion.getLesson());
            //TODO: Something to say quiz is completed

        } else{
            //Move on to next question
            currQuestion = questions.remove();
            setupQuestion();
        }
    }

    private void setupQuestion() {
        switch(currQuestion.getType()) {
            case "sign2eng":
                setContentView(R.layout.multiple_choice);
                makeSign2EngQuestion();
                break;
            case "eng2sign":
                //TODO make multiple choice
                //setContentView(R.layout.eng2sign_mc);
                break;
            case "textEntry":
                setContentView(R.layout.text_entry);
                makeTextEntryQuestion();
                break;
            case "matching":
                //TODO make matching
        //        setContentView(R.layout.matching);
                break;
        }

    }

    //Make the layout for the text entry question
    private void makeTextEntryQuestion(){
        VideoView videoView = findViewById(R.id.questionVideo);

    }

    //Make the layout for the ASL-->English multiple choice question
    private void makeSign2EngQuestion(){
        ImageView imageView = findViewById(R.id.signImage);
        VideoView videoView = findViewById(R.id.signVideo);
        Button[] buttons = new Button[4];
        buttons[0] = (Button)findViewById(R.id.MCFirstGuess);
        buttons[1] = (Button)findViewById(R.id.MCSecondGuess);
        buttons[2] = (Button)findViewById(R.id.MCThirdGuess);
        buttons[3] = (Button)findViewById(R.id.MCFourthGuess);

        int correctIndex = new Random().nextInt(4);
        ArrayList<String> answerList = currQuestion.getWrongAnswersAsList();
        answerList.add(correctIndex, currQuestion.getAnswer());

        for(int i=0; i<4; i++){
            Button currButton = buttons[i];
            currButton.setText(answerList.get(i));
            //currButton.setOnClickListener();
        }

        String fileName = currQuestion.getQuestion();
        System.out.println(fileName);
        String[] fileNameSplit = fileName.split("\\.");
        System.out.println(fileNameSplit.length);
        fileName = fileNameSplit[0];
        if(fileNameSplit[1].equals(("jpg"))) {
            int resID = getResources().getIdentifier(fileName, "drawable", getPackageName());
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(resID);
            imageView.setVisibility(View.VISIBLE);
        } else {
            int resID = getResources().getIdentifier(fileName, "raw", getPackageName());
            android.net.Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resID);
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(this));
            videoView.start();
        }
    }

    private void gotCorrectAnswer(){
        //increment fluency values of related words in the DB
        ArrayList<String> relatedWords = currQuestion.getRelatedWordsAsList();
        for (String word: relatedWords){
            Word upword = dbAccess.selectWord(word).get(0);
            System.out.println("fluency val for " + word + " before: " + upword.getFluencyVal());
            dbAccess.updateFluencyVal(word, 1);
            upword = dbAccess.selectWord(word).get(0);
            System.out.println("Fluency val for " + upword.getWord() + " after: " + upword.getFluencyVal());
        }
        //TODO: Display correct/congrats
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT);
        toast.show();
        nextQuestion();

    }

    private void gotWrongAnswer(){
        //decrement fluency values of related words in the DB
        ArrayList<String> relatedWords = currQuestion.getRelatedWordsAsList();
        for (String word: relatedWords){
            dbAccess.updateFluencyVal(word, -1);
        }
        //TODO: display wrong, show correct answer
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT);
        toast.show();
        questions.add(currQuestion);
        nextQuestion();
    }

    public void MCAnswer(View view){
        chosenAnswer = ((Button)view).getText().toString();
        System.out.println("Answer clicked: " + chosenAnswer);
    }

    public void MCCheckAnswer(View view) {
        if (chosenAnswer.equals(currQuestion.getAnswer())){
            System.out.println("correct!");
            gotCorrectAnswer();

        } else {
            System.out.println("incorrect");
            gotWrongAnswer();
        }
    }

    public void TxtCheckAnswer(View view){
        EditText answerInput = findViewById(R.id.answerInput);
        chosenAnswer = answerInput.getText().toString();
        if (chosenAnswer.equals(currQuestion.getAnswer())){
            System.out.println("correct!");
            gotCorrectAnswer();

        } else {
            System.out.println("incorrect");
            gotWrongAnswer();
        }
    }
}
