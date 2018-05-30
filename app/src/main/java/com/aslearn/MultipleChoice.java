package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 *
 * The Java Class that handles multiple choice questions. This is where
 * all of the code should be for it (or subclasses)
 */
public class MultipleChoice extends AppCompatActivity {
    TextView question;
    VideoView graphic;
    ImageView imgGraphic;
    Button firstGuess;
    Button secondGuess;
    Button thirdGuess;
    Button fourthGuess;
    Button confirmButton;
    Boolean correct; //Whether or not they got the question right

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_choice);

    }


}
