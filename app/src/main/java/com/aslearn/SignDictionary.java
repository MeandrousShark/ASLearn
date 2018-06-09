package com.aslearn;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.aslearn.db.Word;

/**
 * Created by hannonm2 on 6/8/18.
 */

public class SignDictionary extends AppCompatActivity {
    TextView wordView;
    TextView infoView;
    VideoView videoView;
    ImageView imageView;
    Button moreInfoButton;
    private String word;
    private String basicInfo;
    private String moreInfo;
    private String fileName;
    private int floater;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_sign);
        wordView = findViewById(R.id.wordText);
        imageView = findViewById(R.id.signJpg);
        infoView = findViewById(R.id.topInfo);
        videoView = (VideoView) findViewById(R.id.signVideo);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            word = extras.getString("wordName");
            basicInfo = extras.getString("basicInfo");
            moreInfo = extras.getString("moreInfo");
            fileName = extras.getString("visualFile");
        }
        setupSign();
    }

    /**
     * Fills in the views with data about each sign. It also checks if the given media is a picture
     * or a video, and will assign it to the correct view.
     */
    private void setupSign() {
        wordView.setText(word);
        infoView.setText(basicInfo);
        System.out.println(fileName);
        String[] fileNameSplit = fileName.split("\\.");
        System.out.println(fileNameSplit.length);
        fileName = fileNameSplit[0];
        if(fileNameSplit[1].equals(("jpg"))) {
            int resID = getResources().getIdentifier(fileName, "drawable", getPackageName());
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(resID);
            imageView.setVisibility(View.VISIBLE);
        } else {
            int resID = getResources().getIdentifier(fileName, "raw", getPackageName());
            android.net.Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resID);
            videoView.setVideoURI(uri);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            videoView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            videoView.start();
        }
        if(moreInfo == null) {
            moreInfoButton.setEnabled(false);
            moreInfoButton.setAlpha(0.5f);
        } else {
            moreInfoButton.setEnabled(true);
            moreInfoButton.setAlpha(1f);
        }
    }

    /**
     * On pressing the more info button, it will display additional info about a sign if there
     * is any.
     * @param view the more info button
     */
    public void dictMoreInfoButton(View view) {
        //this magical value will change the button's text between back and more info
        floater += 1;
        if(floater % 2 == 1) {
            infoView.setText(moreInfo);
            moreInfoButton.setText(R.string.backButtonText);
            videoView.start();
        } else {
            infoView.setText(basicInfo);
            moreInfoButton.setText(R.string.moreInfoButtonText);
        }
    }
}
