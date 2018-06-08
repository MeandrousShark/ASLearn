package com.aslearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Word;

import java.util.ArrayList;

/**
 * Created by vancoul on 6/8/18.
 */

public class Dictionary extends AppCompatActivity {
    private DatabaseAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess = DatabaseAccess.getInstance(this);
        setContentView(R.layout.dictionary);
    }

    public void searchDictionary(View view){
        EditText searchInput = findViewById(R.id.searchInput);
        String searchWord = searchInput.getText().toString();
        ArrayList<Word> resultList = dbAccess.selectWord(searchWord);
        LinearLayout layout = findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 20, 30, 20);
        layoutParams.height = 250;

    }
}
