package com.aslearn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

/**
 * This is the main menu of our application.
 * From here you can get to anywhere in the app!
 */
public class MainActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.HelloGif);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    /**
     * Loads the module menu activity when the user clicks on "Learn ASL"
     * @param view the current view the user is on
     */
    protected void learnASL(View view) {
        System.out.println("moduleMenu button clicked!");
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void myProgress(View view) {
        Intent intent = new Intent(this, MyProgress.class);
        startActivity(intent);
    }



//    protected void culture(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }
//        protected void dictionary(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }
//        protected void progress(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }

}
