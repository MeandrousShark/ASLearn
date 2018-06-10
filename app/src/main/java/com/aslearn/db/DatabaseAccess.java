package com.aslearn.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    private static final String TABLE_MODULE = "Modules";
    private static final String TABLE_LESSON = "Lessons";
    private static final String TABLE_WORD = "Words";
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_CULTURE = "HistoryAndCultureLessons";

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

    // Get all modules
    public ArrayList<Module> selectAllModules(){
        String sqlQuery = "SELECT module, type, unlocked, completed, module_order FROM " +
                TABLE_MODULE + " ORDER BY module_order";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Module> modules = new ArrayList<>();

        while(cursor.moveToNext()){
            Module currModule = new Module(cursor.getString(0), cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)));
            modules.add(currModule);
        }

        db.close();
        return modules;
    }

    /**
     * Get the history and culture lesson for the specified module
     * @param module the name of the module
     * @return the history and culture lesson object
     */
    public HistoryAndCulture selectCultureLessonByModule(String module){
        String sqlQuery = "SELECT hist_id, module, info FROM " + TABLE_CULTURE +
                " WHERE module = '" + module + "';";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        HistoryAndCulture histAndCultLesson = null;

        if (cursor.moveToNext()){
            histAndCultLesson = new HistoryAndCulture(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2));
        }

        db.close();
        return histAndCultLesson;
    }

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
        String sqlQuery = "SELECT question_id, question, answer, lesson, related_words, type, wrong_answers FROM " +
                TABLE_QUESTIONS + " WHERE lesson = '" + lesson +"'";

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
    public ArrayList<Word> selectSimilarWords(String word){
        String sqlQuery = "SELECT word_id, word, visual_file, basic_info, more_info, lesson, fluency_val FROM " +
                TABLE_WORD + " WHERE word LIKE '" + word +"'";

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

    // Get word info for a specified word
    public Word selectExactWord(String word){
        String sqlQuery = "SELECT word_id, word, visual_file, basic_info, more_info, lesson, fluency_val FROM " +
                TABLE_WORD + " WHERE word = '" + word +"'";

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
        return words.get(0);
    }

    // Get a list of the completed modules
    public ArrayList<String> selectCompletedModules(){
        String sqlQuery = "SELECT module FROM " + TABLE_MODULE + " WHERE completed = 1";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<String> modules = new ArrayList<>();

        while(cursor.moveToNext()){
            modules.add(cursor.getString(0));
        }
        db.close();
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

        ArrayList<String> words = new ArrayList<>();

        while(cursor.moveToNext()){
            words.add(cursor.getString(0));
        }
        db.close();
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

        ArrayList<String> words = new ArrayList<>();

        while(cursor.moveToNext()){
            words.add(cursor.getString(0));
        }
        db.close();
        return words;
    }

    /**
     * Gets the total number of signs the user has learned (fluency val of at least 3)
     * @return an int representing the total # of signs learned
     */
    public int selectNumWordsLearned(){
        String sqlQuery = "SELECT COUNT(*) FROM " + TABLE_WORD + " WHERE fluency_val >= 3";

        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int result = 0;
        if (cursor.moveToNext()){
            result = cursor.getInt(0);
        }

        db.close();
        return result;
    }

    //Updates the fluency value of a word
    public void updateFluencyVal(String word, int i){
        String sqlUpdate = "UPDATE Words " +
                "SET fluency_val = fluency_val + " + i +
                " WHERE word = '" + word + "'";
        db = openHelper.getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    //Marks a finished module as complete and the next module as unlocked
    public void updateFinishedModule(String module){
        String sqlUpdateModuleCompleted = "UPDATE Modules SET completed = 1 " +
                "WHERE module = '"+module+"';";
        String sqlUpdateModuleUnlocked = "UPDATE Modules SET unlocked = 1 " +
                "WHERE module_order = (SELECT module_order +1 FROM Modules " +
                "WHERE module = '"+module+"');";
        db = openHelper.getWritableDatabase();
        db.execSQL(sqlUpdateModuleCompleted);
        db.execSQL(sqlUpdateModuleUnlocked);
        db.close();
    }

    //Marks a finished lesson as complete and the next lesson as unlocked
    public void updateFinishedLesson(String lesson){
        String sqlUpdateCompleted = "UPDATE Lessons SET completed = 1 WHERE lesson_name = '" + lesson + "'";

        String sqlQueryLessonInfo = "SELECT lesson_name, module, unlocked, completed, lesson_order FROM " +
                TABLE_LESSON + " WHERE lesson_name = '" + lesson +"'";
        db = openHelper.getWritableDatabase();

        System.out.println(lesson);

        Cursor cursor = db.rawQuery(sqlQueryLessonInfo, null);
        if(cursor.moveToNext()){
            Lesson lessonInfo = new Lesson(cursor.getString(0), cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Integer.parseInt(cursor.getString(4)));

            System.out.println("Found lesson: "+lessonInfo.getLessonName());

            String sqlUpdateUnlocked = "UPDATE Lessons SET unlocked = 1 " +
                    "WHERE module = '" + lessonInfo.getModuleName() +"' "+
                    "AND lesson_order = (" + (lessonInfo.getLessonOrder()+1) + ");";

            db.execSQL(sqlUpdateCompleted);
            db.execSQL(sqlUpdateUnlocked);

            String sqlSelectLessonCount = "SELECT COUNT(*) FROM Lessons WHERE module = '"+lessonInfo.getModuleName()+"'";
            cursor = db.rawQuery(sqlSelectLessonCount, null);
            cursor.moveToNext();
            int numLessons = cursor.getInt(0);

            db.close();

            //last lesson in the module is completed
            if (numLessons == lessonInfo.getLessonOrder()){
                updateFinishedModule(lessonInfo.getModuleName());
            }
        } else {
            db.close();
        }

    }

}
