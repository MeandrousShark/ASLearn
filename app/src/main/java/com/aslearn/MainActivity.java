package com.aslearn;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                mp.setVolume(0f,0f);
                mp.setLooping(false);
            }
        });
        videoView.start();
    }

    /**
     * Loads the module menu activity when the user clicks on "Learn ASL"
     * @param view the current view the user is on
     */
    public void learnASL(View view) {
        Intent intent = new Intent(this, ModuleMenu.class);
        startActivity(intent);
    }

    /**
     * Loads the dictionary_old activity when the user clicks on "ASL Dictionary"
     * @param view the current view the user is on
     */
    public void ASLDictionary(View view) {
        Intent intent = new Intent(this, Dictionary.class);
        startActivity(intent);
    }

    /**
     * Loads the My Progress activity when the user clicks on "My Progress"
     * @param view the current view the user is on
     */
    public void myProgress(View view) {
        Intent intent = new Intent(this, MyProgress.class);
        startActivity(intent);
    }

}
