package com.aslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This is the main module menu. From here, users can select lessons.
 */
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_menu);
    }

    /**
     * Loads the InfoLesson activity when the user clicks on a lesson. (Currently only
     * the Greetings button works)
     * @param view the current view the user is on
     */
    public void openLesson(View view) {
        //@TODO Figure out the lesson view (where all the signs in the lesson are)
        String buttonID = view.getTag().toString();
        System.out.println(buttonID);
        Intent intent = new Intent(this, InfoLesson.class);
        startActivity(intent);
       // InfoLesson lesson = new InfoLesson(buttonID, view.findViewById(view.getId()));
    }
}
