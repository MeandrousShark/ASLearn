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
 */

public class HistoryLesson extends AppCompatActivity {

  //  private String moduleName;
    private DatabaseAccess dbAccess;
    private HistoryAndCulture histAndCultLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_history);
        Intent intent = getIntent();
       // moduleName = intent.getStringExtra(MainMenu.moduleName);
        dbAccess = DatabaseAccess.getInstance(this);
        histAndCultLesson = dbAccess.selectCultureLessonByModule(intent.getStringExtra(MainMenu.moduleName));
        setupLesson();
    }

    private void setupLesson() {
        TextView titleView = findViewById(R.id.historyTitle);
        TextView infoView = findViewById(R.id.historyText);

        titleView.setText(histAndCultLesson.getModule());
        infoView.setText(histAndCultLesson.getInfo());
    }

    public void finishedModule(View view){
        dbAccess.updateFinishedModule(histAndCultLesson.getModule());
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
