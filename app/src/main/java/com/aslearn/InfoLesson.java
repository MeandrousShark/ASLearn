package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

/**
 *
 * The Java Class that handles the Info Lessons. This is where
 * all of the code should be for it (or subclasses)
 */

public class InfoLesson extends AppCompatActivity{

    TextView title;
    TextView info;
    VideoView videoView;
    //TODO Fix AndroidManifest so that it gets the Android Label from button name

//    public InfoLesson(String buttonName, View view) {
//        setContentView(R.layout.infopage);
//        switch(buttonName) {
//            case "alphaButton":
//                break;
//            case "greetButton":
//                title = findViewById(R.id.WelcomeText);
//                info = findViewById(R.id.topinfo);
//                title.setText(R.string.welcomeLesson);
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
        videoView = (VideoView) findViewById(R.id.infoGif);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
        videoView.setVideoURI(uri);
        videoView.start();
    }

}
