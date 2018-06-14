package com.aslearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Lesson;

import java.util.ArrayList;


/**
 * The sub menu that lets you select the lessons inside a specific module.
 * Builds the list of buttons from the database.
 */
public class LessonMenu extends AppCompatActivity{
    private DatabaseAccess dbAccess;
    private ArrayList<Lesson> lessons;
    private Button[] lessonButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess = DatabaseAccess.getInstance(this);
        setContentView(R.layout.lesson_menu);
        Intent intent = getIntent();
        String moduleName = intent.getStringExtra(MainMenu.moduleName);
        System.out.println(moduleName);
        lessons = dbAccess.selectLessonsByModule(moduleName);
        if (lessons != null) {
            setupLessons();
        }
        setTitle(moduleName);
    }

    //TODO add a button 'Practice Worse Signs' at the bottom- quizzes you on 5(?) worst signs in this section
    /**
     * Makes the lesson buttons for the specified module.  Buttons for lessons that have not yet
     * been unlocked are disabled.
     */
    private void setupLessons() {
        LinearLayout layout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 20, 30, 20);
        layoutParams.height = 250;
        lessonButtons = new Button[lessons.size()];
        for (int i = 0; i < lessonButtons.length; i++) {
            final int I = i;
            Button lessonButton = lessonButtons[i];
            lessonButton = new Button(this);
            lessonButton.setBackground(getDrawable(R.drawable.buttonrounding));
            lessonButton.setText(lessons.get(i).getLessonName());
            lessonButton.setTextSize(24);
            lessonButton.setAllCaps(false);
            lessonButton.setTextColor(Color.GRAY);
            lessonButton.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            lessonButton.setPadding(90,0,90,0);
            layout.addView(lessonButton, layoutParams);

            lessonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LessonMenu.this, InfoLesson.class);
                    intent.putExtra("lessonName", lessons.get(I).getLessonName());
                    startActivity(intent);
                }
            });
            System.out.println(lessons.get(I).getLessonName());
            System.out.println("Completed: "+lessons.get(I).getCompleted());
            System.out.println("Unlocked: "+ lessons.get(I).getUnlocked());

            if (lessons.get(I).getUnlocked() == 0){
                lessonButton.setEnabled(false);
            }
        }
    }
}
