package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

/**
 *
 * The Java Class that handles the Info Lessons. This is where
 * all of the code should be for it (or subclasses)
 */

public class InfoLesson extends AppCompatActivity{

    VideoView videoView;
    //TODO Fix AndroidManifest so that it gets the Android Label from button name

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infopage);
        videoView = (VideoView) findViewById(R.id.infoGif);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
        videoView.setVideoURI(uri);
        videoView.start();
    }

}
