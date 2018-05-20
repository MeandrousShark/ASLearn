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

    TextView wordView;
    TextView infoView;
    VideoView signView;
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
        Intent intent = getIntent();
        String topInfo = intent.getStringExtra(MainMenu.signInfo);
        wordView = findViewById(R.id.wordText);
        wordView.setText(intent.getStringExtra(MainMenu.signName));
        infoView = findViewById(R.id.topinfo);
        infoView.setText(intent.getStringExtra(MainMenu.signInfo));
        signView = (VideoView) findViewById(R.id.signVideo);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
        signView.setVideoURI(uri);
        signView.start();
    }
    protected void MoreInfoButton(View view) {
        System.out.println("moduleMenu button clicked!");
        Intent intent = new Intent(this, MoreInfo.class);
        startActivity(intent);
    }
}
