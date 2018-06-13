package com.aslearn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aslearn.db.DatabaseAccess;
import com.aslearn.db.Word;

import java.util.ArrayList;

/**
 * Created by vancoul on 6/8/18.
 *
 * This activity allows the user to search the database for a particular sign, and then get the
 * info page for that sign.
 */

public class Dictionary extends AppCompatActivity {
    private DatabaseAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAccess = DatabaseAccess.getInstance(this);
        setContentView(R.layout.dictionary_old);
    }

    /**
     * Selects words from the database that are similar to the user's text input. A list of the
     * results is then displayed to the user, and the user can click on a word to view the info page.
     * @param view the search button
     */
    public void searchDictionary(View view) {
        //TODO Bug: When Entering multiple inputs to the dictionary search, it will just keep adding things
        EditText searchInput = findViewById(R.id.searchInput);
        String searchWord = searchInput.getText().toString();
        final ArrayList<Word> resultList = dbAccess.selectSimilarWords(searchWord);
        final LinearLayout layout = findViewById(R.id.DictionaryEntries);

        layout.removeAllViewsInLayout();
        if (resultList.isEmpty()){
            //TODO: display "Sorry, no results found" to the user
            TextView resultView = new TextView(this);
            resultView.setText("Sorry, no match found");
            resultView.setTextSize(24);
            //resultView.setPadding(90, 0, 90, 0);
            resultView.setGravity(Gravity.CENTER_HORIZONTAL);
            layout.addView(resultView);

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
