package com.aslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aslearn.db.DatabaseManager;
import com.aslearn.db.Lesson;

import java.util.ArrayList;


public class LessonMenu extends AppCompatActivity{
    private DatabaseManager dbManager;
    private ArrayList<Lesson> lessons;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.lesson_menu);
        lessons = dbManager.selectLessonsByModule("Alphabet");
        setupLessons();
    }

    private void setupLessons() {
        test = findViewById(R.id.textView2);
        test.setText("");
        for(Lesson lesson : lessons) {
            test.append(lesson.getLessonName());
        }
    }
}
