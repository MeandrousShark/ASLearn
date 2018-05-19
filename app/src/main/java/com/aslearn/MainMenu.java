package com.aslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This is the main module menu. From here, users can select lessons.
 */
public class MainMenu extends AppCompatActivity {


    public static final String signInfo = "com.aslearn.welcomeInfo";
    public static final String title = "com.aslearn.title";

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
        Intent intent = new Intent(this, InfoLesson.class);
        intent.putExtra(title, R.string.welcomeLesson);
        intent.putExtra(signInfo, R.string.welcomeInfo);
        startActivity(intent);
       // InfoLesson lesson = new InfoLesson(buttonID, view.findViewById(view.getId()));
    }

}
