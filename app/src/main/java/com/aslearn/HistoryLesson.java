package com.aslearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hannonm2 on 6/8/18.
 */

public class HistoryLesson extends AppCompatActivity {

    private String moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getStringExtra("moduleName");
    }

    private void setupLesson() {

    }
}
