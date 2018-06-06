package com.aslearn;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class FingerSpelling extends AppCompatActivity {
    private String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerspelling_questions);
        ImageView signs = findViewById(R.id.questionVideo);
        signs.setBackgroundResource(R.drawable.apple_word);
        AnimationDrawable fsAnimation = (AnimationDrawable) signs.getBackground();
        fsAnimation.start();
        word = "apple";
    }

    public void checker(View view) {
        EditText answerInput = findViewById(R.id.answerInput);
        String answer = answerInput.getText().toString();
        Context context = getApplicationContext();
        if(answer.equalsIgnoreCase(word)) {
            Toast toast = Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(context, "Incorrect!", Toast.LENGTH_SHORT);
            toast.show();
            answerInput.setText("");
        }
    }
}
