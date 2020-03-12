package com.aslearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.HistoryAndCulture;

/**
 * Created by hannonm2 on 6/8/18.
 *
 * This activity shows the history and culture lesson for a selected module.
 */

public class HistoryLesson extends AppCompatActivity {

    private DatabaseAccess dbAccess;
    private HistoryAndCulture histAndCultLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_history);
        Intent intent = getIntent();
        dbAccess = DatabaseAccess.getInstance(this);
        histAndCultLesson = dbAccess.selectCultureLessonByModule(intent.getStringExtra(ModuleMenu.moduleName));
        setupLesson();
    }

    /**
     * Updates the view to show the information from the relevant history and culture lesson.
     */
    private void setupLesson() {
        TextView titleView = findViewById(R.id.historyTitle);
        TextView infoView = findViewById(R.id.historyText);

        titleView.setText(histAndCultLesson.getModule());
        infoView.setText(histAndCultLesson.getInfo());
    }

    /**
     * The onClick listener for the "Back to Lessons" button that marks the module as complete in
     * the database and brings the user back to the module menu.
     * @param view the "Back to Lessons" button
     */
    public void finishedModule(View view){
        dbAccess.updateFinishedModule(histAndCultLesson.getModule());
        Intent intent = new Intent(this, ModuleMenu.class);
        startActivity(intent);
    }
}
