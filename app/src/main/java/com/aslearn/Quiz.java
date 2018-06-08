package com.aslearn;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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
    private View selectedView;
    private View correctView;

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
            setContentView(R.layout.finished_activity);
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
                setContentView(R.layout.eng2sign_mc);
                makeEng2SignQuestion();
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

        String fileName = currQuestion.getQuestion();
        String[] fileNameSplit = fileName.split("\\.");
        fileName = fileNameSplit[0];
        int resID = getResources().getIdentifier(fileName, "raw", getPackageName());
        android.net.Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resID);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoView.start();
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
            if (i == correctIndex){
                correctView = currButton;
            }
            //currButton.setOnClickListener();
        }

        String fileName = currQuestion.getQuestion();
        switchGraphic(fileName, imageView, videoView);
    }
    private void makeEng2SignQuestion(){
        TextView questionTextView = findViewById(R.id.MCQuestion);
        VideoView[] videoViews = new VideoView[4];
        ImageView[] imageViews = new ImageView[4];

        videoViews[0] = findViewById(R.id.MCVideo1);
        videoViews[1] = findViewById(R.id.MCVideo2);
        videoViews[2] = findViewById(R.id.MCVideo3);
        videoViews[3] = findViewById(R.id.MCVideo4);

        imageViews[0] = findViewById(R.id.MCImage1);
        imageViews[1] = findViewById(R.id.MCImage2);
        imageViews[2] = findViewById(R.id.MCImage3);
        imageViews[3] = findViewById(R.id.MCImage4);

        int correctIndex = new Random().nextInt(4);
        ArrayList<String> answerList = currQuestion.getWrongAnswersAsList();
        answerList.add(correctIndex, currQuestion.getAnswer());

        String questionText = "What is the sign for '"+ currQuestion.getQuestion() + "'?";
        questionTextView.setText(questionText);

        for(int i=0; i<4; i++){
            boolean isImageView = switchGraphic(answerList.get(i), imageViews[i], videoViews[i]);
            if (isImageView){
                imageViews[i].setContentDescription(answerList.get(i));
                System.out.println("Content Description for image is: " + imageViews[i].getContentDescription().toString());
                correctView = imageViews[i];
            } else {
                videoViews[i].setContentDescription(answerList.get(i));
                System.out.println("Content Description for video is: " + videoViews[i].getContentDescription().toString());
                correctView = videoViews[i];
            }
        }
    }

    private boolean switchGraphic(String fileName, ImageView imageView, VideoView videoView){
        System.out.println(fileName);
        String[] fileNameSplit = fileName.split("\\.");
        System.out.println(fileNameSplit.length);
        fileName = fileNameSplit[0];
        if(fileNameSplit[1].equals(("jpg"))) {
            int resID = getResources().getIdentifier(fileName, "drawable", getPackageName());
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(resID);
            imageView.setVisibility(View.VISIBLE);
            return true;
        } else {
            int resID = getResources().getIdentifier(fileName, "raw", getPackageName());
            android.net.Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resID);
            videoView.setVideoURI(uri);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            videoView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            videoView.start();
            return false;
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
        Toast toast = Toast.makeText(context, "Incorrect \n Correct Answer: " + currQuestion.getAnswer(), Toast.LENGTH_SHORT);
        toast.show();
        questions.add(currQuestion);
        nextQuestion();
    }

    public void MCAnswer(View view){
        chosenAnswer = ((Button)view).getText().toString();
        if (selectedView != null){
            selectedView.setSelected(false);
        }
        selectedView = view;
        view.setSelected(true);
        System.out.println("Answer clicked: " + chosenAnswer);

    }

    public void MCEng2SignAnswer(View view){
        chosenAnswer = view.getContentDescription().toString();
        System.out.println("Answer clicked: " + chosenAnswer);
        if (selectedView != null){
            selectedView.setSelected(false);
        }
        selectedView = view;
        view.setSelected(true);
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
        if (chosenAnswer.equalsIgnoreCase(currQuestion.getAnswer())){
            System.out.println("correct!");
            gotCorrectAnswer();
        } else {
            System.out.println("incorrect");
            gotWrongAnswer();
        }
    }

    public void backToLessons(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
