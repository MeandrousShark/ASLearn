package com.aslearn.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    private static final String TABLE_LESSON = "Lessons";
    private static final String TABLE_WORD = "Words";

   // Cursor cursor = null;

    private DatabaseAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    // Get a single instance of the database
    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

//    // Open database connection
//    public void openDB(){
//        this.db = openHelper.getWritableDatabase();
//    }
//
//    // Close the database connection
//    public void closeDB(){
//        if(db != null){
//            this.db.close();
//        }
//    }

    //Get all lessons from the specified module
    public ArrayList<Lesson> selectLessonsByModule(String module){
        String sqlQuery = "SELECT lesson_name, module, unlocked, completed, lesson_order FROM " +
                TABLE_LESSON + " WHERE module = '" + module +"' ORDER BY lesson_order";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();

        while(cursor.moveToNext()){
            Lesson currLesson = new Lesson(cursor.getString(0), cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)));
            lessons.add(currLesson);
        }
        db.close();
        return lessons;
    }

    //Get all words from the specified lesson
    public ArrayList<Word> selectWordsByLesson(String lesson){
        String sqlQuery = "SELECT word_id, word, visual_file, basic_info, more_info, lesson, fluency FROM " +
                TABLE_WORD + "WHERE lesson = '" + lesson +"'";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Word> words = new ArrayList<Word>();

        while(cursor.moveToNext()){
            Word currWord = new Word(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)));
            words.add(currWord);
        }
        db.close();
        return words;
    }
}
