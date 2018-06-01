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
    private static final String TABLE_QUESTIONS = "Questions";

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
        String sqlQuery = "SELECT word_id, word, visual_file, basic_info, more_info, lesson, fluency_val FROM " +
                TABLE_WORD + " WHERE lesson = '" + lesson +"'";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Word> words = new ArrayList<Word>();

        while(cursor.moveToNext()){
            Word currWord = new Word(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), Integer.parseInt(cursor.getString(6)));
            words.add(currWord);
        }
        db.close();
        return words;
    }

    //Get all questions from the specified lesson
    public ArrayList<Question> selectQuestionsByLesson(String lesson){
        String sqlQuery = "SELECT question_id, question, answer, lesson, related_words, visual_file, type FROM " +
                TABLE_QUESTIONS + "WHERE lesson = '" + lesson +"'";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Question> questions = new ArrayList<Question>();

        while(cursor.moveToNext()){
            Question currQuest = new Question(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6));
            questions.add(currQuest);
        }
        db.close();
        return questions;
    }

    // Get word info for a specified word
    public ArrayList<Word> selectWord(String word){
        String sqlQuery = "SELECT word_id, word, visual_file, basic_info, more_info, lesson, fluency_val FROM " +
                TABLE_WORD + "WHERE word LIKE '" + word +"'";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Word> words = new ArrayList<Word>();

        while(cursor.moveToNext()){
            Word currWord = new Word(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), Integer.parseInt(cursor.getString(6)));
            words.add(currWord);
        }
        db.close();
        return words;
    }

    // Get a list of the completed modules
    public ArrayList<String> selectCompletedModules(){
        String sqlQuery = "SELECT module FROM " +
                " (SELECT * FROM Lessons GROUP BY module)" +
                " WHERE completed = 1";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<String> modules = new ArrayList<String>();

        while(cursor.moveToNext()){
            modules.add(cursor.getString(0));
        }
        return modules;
    }

    //Get the five best words from completed lessons
    public ArrayList<String> select5BestWords(){
        String sqlQuery = "SELECT word from Lessons JOIN Words " +
                "ON Lessons.lesson_name = Words.lesson " +
                "WHERE Lessons.completed = 1 " +
                "ORDER BY Words.fluency_val DESC LIMIT 5";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<String> words = new ArrayList<String>();

        while(cursor.moveToNext()){
            words.add(cursor.getString(0));
        }
        return words;
    }

    //Get the five worst words from completed lessons
    public ArrayList<String> select5WorstWords(){
        String sqlQuery = "SELECT word from Lessons JOIN Words " +
                "ON Lessons.lesson_name = Words.lesson " +
                "WHERE Lessons.completed = 1 " +
                "ORDER BY Words.fluency_val ASC LIMIT 5";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<String> words = new ArrayList<String>();

        while(cursor.moveToNext()){
            words.add(cursor.getString(0));
        }
        return words;
    }

}
