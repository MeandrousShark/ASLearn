package com.aslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is the main module menu. From here, users can select lessons.
 */
public class MainMenu extends AppCompatActivity {


    public static final String moduleName = "com.aslearn.moduleName";
    public static final String signInfo = "com.aslearn.infoText";

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
    public void openLessonMenu(View view) {
        //@TODO Figure out the lesson view (where all the signs in the lesson are)
        Intent intent = new Intent(this, LessonMenu.class);
//        String signName = "Welcome";
//        String infoSection = "This is where the \nhow to sign info will go";
        Button moduleSelected = (Button) view;
        String module = moduleSelected.getText().toString();
        intent.putExtra(MainMenu.moduleName, module);
//        intent.putExtra(signInfo, infoSection);
        startActivity(intent);
       // InfoLesson lesson = new InfoLesson(buttonID, view.findViewById(view.getId()));
    }

    public void runMultipleChoice(View view) {
        Intent intent = new Intent(this, MultipleChoice.class);
        startActivity(intent);
    }
}
