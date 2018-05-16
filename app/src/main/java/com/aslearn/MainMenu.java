package com.aslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_menu);
    }

    /**
     *
     * @param view
     */
    public void greetings(View view) {
        //@TODO Figure out the lesson view (where all the signs in the lesson are)
         Intent intent = new Intent(this, InfoLesson.class);
         startActivity(intent);
    }
}
