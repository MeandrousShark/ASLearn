package com.aslearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Loads the module menu activity when the user clicks on "Learn ASL"
     * @param view the current view the user is on?
     */
    protected void learnASL(View view) {
        System.out.println("moduleMenu button clicked!");
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
//    protected void culture(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }
//        protected void dictionary(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }
//        protected void progress(View view) {
//        System.out.println("moduleMenu button clicked!");
//        Intent intent = new Intent(this, MainMenu.class);
//        startActivity(intent);
//    }

}
