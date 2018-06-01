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

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Word;

import java.util.ArrayList;

//import com.aslearn.db.AppDatabase;

/**
 *
 * The Java Class that handles the Info Lessons. This is where
 * all of the code should be for it (or subclasses)
 */

public class InfoLesson extends AppCompatActivity{

    TextView wordView;
    TextView infoView;
    VideoView videoView;
    ImageView imageView;
    Intent intent;
    Button moreInfoButton;
    private int floater;
    private Word word;
    private ArrayList<Word> words;
    private DatabaseAccess dbAccess;
    private int index;

   // AppDatabase appDatabase;

    //TODO Fix AndroidManifest so that it gets the Android Label from button name

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = 0;
        dbAccess = DatabaseAccess.getInstance(this);
        Intent intent = getIntent();
        words = dbAccess.selectWordsByLesson(intent.getStringExtra("lessonName"));
        setContentView(R.layout.infopage);
        wordView = findViewById(R.id.wordText);
        imageView = findViewById(R.id.signJpg);
        infoView = findViewById(R.id.topInfo);
        videoView = (VideoView) findViewById(R.id.signVideo);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        setupSign();
    }

    private void setupSign() {
        word = words.get(index);
        wordView.setText(word.getWord());
        infoView.setText(word.getBasicInfo());
        String fileName = word.getVisualFile();
        System.out.println(fileName);
        String[] fileNameSplit = fileName.split("\\.");
        System.out.println(fileNameSplit.length);
        fileName = fileNameSplit[0];
        int resID = getResources().getIdentifier(fileName, "drawable", getPackageName());
        if(fileNameSplit[1].equals(("jpg"))) {
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageResource(resID);
            imageView.setVisibility(View.VISIBLE);
        } else {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.welcome);
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(this));
            videoView.start();
        }
    }

    protected void moreInfoButton(View view) {
        floater += 1;
        if(floater % 2 == 1) {
            infoView.setText(word.getMoreInfo());
            moreInfoButton.setText(R.string.backButtonText);
            videoView.start();
        } else {
            infoView.setText(word.getBasicInfo());
            moreInfoButton.setText(R.string.moreInfoButtonText);
        }
    }


    public void nextSign(View view) {
        index++;
        if(index < words.size()) {
            setupSign();
        } else {
            //TODO go to quiz
        }
    }
}
