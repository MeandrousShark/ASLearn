package com.aslearn;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;


public class FingerSpelling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerspelling_questions);

        String word = "Apple";
        // setImages(word);
    }
//todo michael will fix this
    /*private void setImages(String word)  {
        ImageView images = findViewById(R.id.fsDisplay);
        AnimationDrawable letterSigns = new AnimationDrawable();
        String[] letters = new String[word.length()];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = word.substring(i, i+1).toLowerCase();
            String location = "R.drawable." + letters[i] +".jpg";
            int fileLocation = getResources().getIdentifier(location, null, getCallingPackage());
            letterSigns.addFrame(getResources().getDrawable(fileLocation, null), 1000);
        }
        letterSigns.setOneShot(false);
        images.setBackground(letterSigns);
    }
    */
}
