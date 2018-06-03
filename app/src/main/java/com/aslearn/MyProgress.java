package com.aslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.aslearn.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hannonm2 on 5/30/18.
 */

public class MyProgress extends AppCompatActivity {
    TextView numSignsLearned;
    TextView bestWords;
    TextView worstWords;
    HashMap<String, ImageView> badges;

    private DatabaseAccess dbAccess;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess = DatabaseAccess.getInstance(this);
        setContentView(R.layout.myprogress);
        worstWords = findViewById(R.id.WorstSignsColumn);
        bestWords = findViewById(R.id.BestSignsColumn);
        numSignsLearned = findViewById(R.id.NumberLearnedSigns);

        badges.put("Alphabet", (ImageView)findViewById(R.id.Badge1));
        badges.put("Numbers", (ImageView)findViewById(R.id.Badge2));
        badges.put("Greetings", (ImageView)findViewById(R.id.Badge3));
        badges.put("Food", (ImageView)findViewById(R.id.Badge4));
        badges.put("Basic Verbs", (ImageView)findViewById(R.id.Badge5));
        badges.put("Giving Directions", (ImageView)findViewById(R.id.Badge6));
        badges.put("Basic Adjectives", (ImageView)findViewById(R.id.Badge7));
        badges.put("Family", (ImageView)findViewById(R.id.Badge8));
        badges.put("Weather", (ImageView)findViewById(R.id.Badge9));
        badges.put("Questions", (ImageView)findViewById(R.id.Badge10));

        //setNumSignsLearned();
        setBestWords();
        setWorstWords();
        setBadges();
    }



    /*
    **
    **the database is not set up for this yet
    **
    **
    private void setNumSignsLearned(){
        ArrayList<String> totalSignsLearned = dbAccess.selectNumSignsLearned();
        String text = "Number of Signs Learned:\n"+ length.totalSignsLearned() + " signs!";
        bestWords.setText(text);
    }

*/

    private void setBestWords(){
        ArrayList<String> bestWordList = dbAccess.select5BestWords();
        String text = "Great Work!:\n";
        for (String word:bestWordList) {
            text += word + "\n";
        }
        bestWords.setText(text);
    }


    private void setWorstWords(){
        ArrayList<String> worstWordList = dbAccess.select5WorstWords();
        String text = "Could Improve:\n";
        for (String word:worstWordList) {
            text += word + "\n";
        }
        worstWords.setText(text);
    }

    private void setBadges(){
        ArrayList<String> completedModules = dbAccess.selectCompletedModules();
        ImageView currView;
        for (String module:completedModules) {
            currView = badges.get(module);
            currView.setVisibility(View.INVISIBLE);
        }
    }
}
