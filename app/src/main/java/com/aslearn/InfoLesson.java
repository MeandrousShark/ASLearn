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
    private int index;

   // AppDatabase appDatabase;

    /**
     * Finds the views from the xml, loads the database to an array of Words, then
     * @param savedInstanceState used for the super method call
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        index = 0;
        setContentView(R.layout.infopage);
        wordView = findViewById(R.id.wordText);
        imageView = findViewById(R.id.signJpg);
        infoView = findViewById(R.id.topInfo);
        videoView = (VideoView) findViewById(R.id.signVideo);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
        words = dbAccess.selectWordsByLesson(intent.getStringExtra("lessonName"));
        System.out.println("Number of words: " + words.size());
        setupSign();
    }

    /**
     * Fills in the views with data about each sign. It also checks if the given media is a picture
     * or a video, and will assign it to the correct view.
     */
    private void setupSign() {
        word = words.get(index);
        System.out.println("Word: " + word.getWord());
        if (wordView == null){
            System.out.println("Wordview null");
        }
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

    /**
     * On pressing the more info button, it will display additional info about a sign if there
     * is any.
     * @param view the more info button
     */
    protected void moreInfoButton(View view) {
        //this magical value will change the button's text between back and more info
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

    /**
     * Loads the next sign.  If it has reached the end of the index, it will present the quiz
     * for the lesson.
     * @param view the nextSign button
     */
    public void nextSign(View view) {
        index++;
        if(index < words.size()) {
            setupSign();
        } else {
            Intent intent = new Intent(this, Quiz.class);
            intent.putExtra("lessonName", word.getLesson());
            startActivity(intent);
        }
    }
}
