package com.aslearn;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Module;

import java.util.ArrayList;

/**
 * This is the main module menu. From here, users can select lessons.
 */
public class MainMenu extends AppCompatActivity {


    public static final String moduleName = "com.aslearn.moduleName";
    public static final String signInfo = "com.aslearn.infoText";
    private ArrayList<Module> modules;
    private DatabaseAccess dbAccess;
    Button[] moduleButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.module_menu);
        setContentView(R.layout.lesson_menu);
        dbAccess = DatabaseAccess.getInstance(this);
        modules = dbAccess.selectAllModules();
        if (modules != null) {
            setupModules();
        }
    }

    //TODO add a button 'Practice Worse Signs' at the bottom- quizzes you on 5(?) worst signs in this section
    private void setupModules() {
        LinearLayout layout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 20, 30, 20);
        layoutParams.height = 250;
        moduleButtons = new Button[modules.size()];
        for (int i = 0; i < moduleButtons.length; i++) {
            final int I = i;
            Button moduleButton = moduleButtons[i];
            moduleButton = new Button(this);
            moduleButton.setBackground(getDrawable(R.drawable.buttonrounding));
            moduleButton.setText(modules.get(i).getModuleName());
            moduleButton.setTextSize(24);
            moduleButton.setAllCaps(false);
            moduleButton.setTextColor(Color.GRAY);
            moduleButton.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            moduleButton.setPadding(90,0,90,0);
            layout.addView(moduleButton, layoutParams);
            if (modules.get(I).getType().equals("history")){
                moduleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openHistoryLesson(v);
                    }
                });
            } else {
                moduleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openLessonMenu(v);
                    }
                });
            }
            System.out.println(modules.get(I).getModuleName());
            System.out.println("Completed: "+modules.get(I).getCompleted());
            System.out.println("Unlocked: "+ modules.get(I).getUnlocked());
            if (modules.get(I).getUnlocked() == 0){
          //      moduleButton.setEnabled(false);
            }
        }
    }

    /**
     * Loads the HistoryLesson activity
     * @param view the menu item that was clicked
     */
    public void openHistoryLesson(View view) {
        Intent intent = new Intent(this, HistoryLesson.class);
        Button moduleSelected = (Button) view;
        String module = moduleSelected.getText().toString();
        intent.putExtra(MainMenu.moduleName, module);
        startActivity(intent);
    }

    /**
     * Loads the LessonMenu activity when the user clicks on a lesson.
     * @param view the menu item that was clicked
     */
    //TODO add percentage sign to right side of button that indicates amount of lesson completed
    public void openLessonMenu(View view) {
        Intent intent = new Intent(this, LessonMenu.class);
        Button moduleSelected = (Button) view;
        String module = moduleSelected.getText().toString();
        intent.putExtra(MainMenu.moduleName, module);
        startActivity(intent);
    }
}
