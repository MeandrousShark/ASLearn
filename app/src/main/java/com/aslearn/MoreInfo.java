package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

/**
 *
 * The Java Class that handles the More Information Page. This is where
 * all of the code should be for it (or subclasses)
 */

public class MoreInfo extends AppCompatActivity{
    VideoView videoView;
    //TODO Fix AndroidManifest so that it gets the Android Label from button name

    protected void learnASL(View view) {
        System.out.println("moduleMenu button clicked!");
        Intent intent = new Intent(this, MoreInfo.class);
        startActivity(intent);
    }
}
