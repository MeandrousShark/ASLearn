package com.aslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aslearn.db.DatabaseAccess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hannonm2 on 5/30/18.
 *
 * This activity shows the user their progress. It displays the number of words learned, the five
 * best and worst words, and the badges for completed modules.
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

        badges = new HashMap<>();
        badges.put("Alphabet", (ImageView)findViewById(R.id.alphabetBadge));
        badges.put("Numbers", (ImageView)findViewById(R.id.numbersBadge));
        badges.put("Greetings", (ImageView)findViewById(R.id.greetingsBadge));
        badges.put("Food", (ImageView)findViewById(R.id.foodBadge));
        badges.put("Verbs", (ImageView)findViewById(R.id.verbBadge));
        badges.put("Adjectives", (ImageView)findViewById(R.id.adjectiveBadge));
        badges.put("Family", (ImageView)findViewById(R.id.familyBadge));
        badges.put("Forming Sentences", (ImageView)findViewById(R.id.cultureBadge));

        setNumSignsLearned();
        setBestWords();
        setWorstWords();
        setBadges();
    }


    /**
     * Sets the text to show the number of signs learned
     */
    private void setNumSignsLearned(){
        int totalSignsLearned = dbAccess.selectNumWordsLearned();
        String text = "Number of Signs Learned:\n"+ totalSignsLearned + " signs!";
        numSignsLearned.setText(text);
    }


    /**
     * Sets the text for the 5 best words
     */
    private void setBestWords(){
        ArrayList<String> bestWordList = dbAccess.select5BestWords();
        String text = "Great Work!\n";
        for (String word:bestWordList) {
            text += word + "\n";
        }
        bestWords.setText(text);
    }

    /**
     * Sets the text for the 5 worst words
     */
    private void setWorstWords(){
        ArrayList<String> worstWordList = dbAccess.select5WorstWords();
        String text = "Could Improve\n";
        for (String word:worstWordList) {
            text += word + "\n";
        }
        worstWords.setText(text);
    }

    /**
     * Sets the badges for completed modules as visible
     */
    private void setBadges(){
        ArrayList<String> completedModules = dbAccess.selectCompletedModules();
        ImageView currView;
        for (String module:completedModules) {
            currView = badges.get(module);
            if (currView != null) {
                currView.setVisibility(View.VISIBLE);
            }
        }
    }
}
