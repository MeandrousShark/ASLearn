package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

//import com.aslearn.db.AppDatabase;

/**
 *
 * The Java Class that handles the Info Lessons. This is where
 * all of the code should be for it (or subclasses)
 */

public class InfoLesson extends AppCompatActivity{

    TextView wordView;
    TextView infoView;
    VideoView signView;
    Intent intent;
    Button moreInfoButton;
    private int floater;

   // AppDatabase appDatabase;

    //TODO Fix AndroidManifest so that it gets the Android Label from button name

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopage);
        //intent = getIntent();
        floater = 0;
       // appDatabase = AppDatabase.getInstance(InfoLesson.this); //idk if this will workkk
        //String topInfo = intent.getStringExtra(MainMenu.signInfo);
        wordView = findViewById(R.id.wordText);
        //wordView.setText(intent.getStringExtra(MainMenu.signName));
        infoView = findViewById(R.id.topInfo);
        //infoView.setText(topInfo);
        signView = (VideoView) findViewById(R.id.signVideo);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome); //TODO Make all of this code in a new class
        signView.setVideoURI(uri);
        signView.setMediaController(new MediaController(this));
        signView.start();
    }

    protected void moreInfoButton(View view) {
        floater += 1;

        if(floater % 2 == 1) {
            infoView.setText(R.string.moreInfo);
            moreInfoButton.setText(R.string.backButtonText);
            signView.start();
        } else {
            infoView.setText(getIntent().getStringExtra(MainMenu.signInfo));
            moreInfoButton.setText(R.string.moreInfoButtonText);
        }
    }


    public void quizStart(View view) {
        Intent intent = new Intent(this, FingerSpelling.class);
        startActivity(intent);
    }
}
