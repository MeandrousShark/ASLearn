package com.aslearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.dictionary_old);
    }

    public void searchDictionary(View view) {
        //TODO Bug: When Entering multiple inputs to the dictionary search, it will just keep adding things
        EditText searchInput = findViewById(R.id.searchInput);
        String searchWord = searchInput.getText().toString();
        final ArrayList<Word> resultList = dbAccess.selectSimilarWords(searchWord);
        final LinearLayout layout = findViewById(R.id.DictionaryEntries);
        layout.removeAllViewsInLayout();
        if (resultList.isEmpty()){
            //TODO: display "Sorry, no results found" to the user

        }else {
            Button[] resultButtons = new Button[resultList.size()];

            for (int i = 0; i < resultButtons.length; i++) {
                final int I = i;
                Button resultButton;
                resultButton = new Button(this);
                resultButton.setBackground(getDrawable(R.drawable.buttonrounding));
                // lessonButton.setBackgroundColor(0xFFFFFE);
                resultButton.setText(resultList.get(i).getWord());
                resultButton.setTextSize(24);
                resultButton.setTextColor(Color.GRAY);
                resultButton.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                resultButton.setPadding(90, 0, 90, 0);
                layout.addView(resultButton);
                resultButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Dictionary.this, SignDictionary.class);
                        intent.putExtra("wordName", resultList.get(I).getWord());
                        intent.putExtra("basicInfo", resultList.get(I).getBasicInfo());
                        intent.putExtra("moreInfo", resultList.get(I).getMoreInfo());
                        intent.putExtra("visualFile", resultList.get(I).getVisualFile());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
