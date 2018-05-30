package com.aslearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.aslearn.db.DatabaseManager;
import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Lesson;

import java.util.ArrayList;



public class LessonMenu extends AppCompatActivity{
  //  private DatabaseManager dbManager;
    private DatabaseAccess dbManager;
    private ArrayList<Lesson> lessons;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  dbManager = new DatabaseManager(this);
        dbManager = DatabaseAccess.getInstance(this);
        setContentView(R.layout.lesson_menu);
        Intent intent = getIntent();
        String moduleName = intent.getStringExtra(MainMenu.moduleName);
        System.out.println(moduleName);
        lessons = dbManager.selectLessonsByModule(moduleName);
        if (lessons != null) {
            setupLessons();
        }
    }

    //TODO add a button 'Practice Worse Signs' at the bottom- quizzes you on 5(?) worst signs in this section
    private void setupLessons() {
        LinearLayout layout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 20, 30, 20);
        layoutParams.height = 300;
        Button[] lessonButtons = new Button[lessons.size()];
        for (int i = 0; i < lessonButtons.length; i++) {
            Button lessonButton = lessonButtons[i];
            lessonButton = new Button(this);
            lessonButton.setBackground(getDrawable(R.drawable.buttonrounding));
           // lessonButton.setBackgroundColor(0xFFFFFE);
            lessonButton.setText(lessons.get(i).getLessonName());
            lessonButton.setTextSize(24);
            lessonButton.setTextColor(Color.GRAY);
            lessonButton.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            lessonButton.setPadding(90,0,90,0);
            layout.addView(lessonButton, layoutParams);
        }
    }
}
