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
    //TODO Fix AndroidManifest so that it gets the Android Label from button name

//    public InfoLesson(String buttonName, View view) {
//        setContentView(R.layout.infopage);
//        switch(buttonName) {
//            case "alphaButton":
//                break;
//            case "greetButton":
//                signName = findViewById(R.id.WelcomeText);
//                info = findViewById(R.id.topinfo);
//                signName.setText(R.string.welcomeLesson);
//                info.setText(R.string.welcomeInfo);
//                videoView = (VideoView) findViewById(R.id.HelloGif);
//                Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
//                videoView.setVideoURI(uri);
//                videoView.start();
//        }
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopage);
        intent = getIntent();
        floater = 0;
        String topInfo = intent.getStringExtra(MainMenu.signInfo);
        wordView = findViewById(R.id.wordText);
        wordView.setText(intent.getStringExtra(MainMenu.signName));
        infoView = findViewById(R.id.topInfo);
        infoView.setText(topInfo);
        signView = (VideoView) findViewById(R.id.signVideo);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome); //TODO make this modular
        signView.setVideoURI(uri);
        signView.setMediaController(new MediaController(this));
        signView.start();
    }

    protected void moreInfoButton(View view) {
        floater += 1;
        System.out.println("don't copy me plz");

        if(floater % 2 == 1) {
            infoView.setText(R.string.moreInfo);
            moreInfoButton.setText(R.string.backButtonText);
            signView.start();
        } else {
            infoView.setText(getIntent().getStringExtra(MainMenu.signInfo));
            moreInfoButton.setText(R.string.moreInfoButtonText);
        }
    }

}
