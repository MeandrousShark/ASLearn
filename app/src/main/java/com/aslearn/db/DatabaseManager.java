package com.aslearn.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="aslDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LESSON = "Lessons";
    private static final String TABLE_WORD = "Words";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createLessons =
                "CREATE TABLE `Lessons` ( `lesson_name` TEXT NOT NULL PRIMARY KEY, " +
                                        "`module` TEXT NOT NULL, "+
                                        "`unlocked` INTEGER NOT NULL DEFAULT 0, " +
                                        "`completed` INTEGER NOT NULL DEFAULT 0, " +
                                        "`lesson_order` INTEGER NOT NULL )";
        String createWords =
                "CREATE TABLE `Words` ( `word_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                        "`word` TEXT NOT NULL, " +
                                        "`visual_file` TEXT NOT NULL, " +
                                        "`basic_info` TEXT NOT NULL, " +
                                        "`more_info` TEXT, " +
                                        "`lesson` TEXT NOT NULL, " +
                                        "`fluency` INTEGER NOT NULL DEFAULT 0, " +
                                        "FOREIGN KEY(`lesson`) REFERENCES `Lessons`(`lesson_name`) )";
        db.execSQL(createLessons);
        db.execSQL(createWords);

        //add some lessons to the DB - will be replaced later
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("A-G", "Alphabet", 1, 0, 1));
        lessons.add(new Lesson("H-N", "Alphabet", 0, 0, 2));
        lessons.add(new Lesson("O-U", "Alphabet", 0, 0, 3));
        lessons.add(new Lesson("V-Z", "Alphabet", 0, 0, 4));

        for(int i=0; i<lessons.size(); i++){
            Lesson lesson = lessons.get(i);
            String sqlInsert = "INSERT INTO " + TABLE_LESSON + " (module, lesson_name, unlocked, lesson_order)" +
                    "VALUES ('"+ lesson.getModuleName()+"', '"+ lesson.getLessonName() +"', "
                    +lesson.getUnlocked()+", "+ lesson.getLessonOrder()+")";
            db.execSQL(sqlInsert);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(db);
    }

    //Insert a lesson into the lessons table
    public void insertLesson(Lesson lesson){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_LESSON + " (module, lesson_name, unlocked, lesson_order)" +
                            "VALUES ('"+ lesson.getModuleName()+"', '"+ lesson.getLessonName() +"', "
                            +lesson.getUnlocked()+", "+ lesson.getLessonOrder()+")";
        db.execSQL(sqlInsert);
        db.close();
    };

    //Insert a word into the words table
    public void insertWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_WORD + "(word_id, word, visual_file, basic_info, more_info, lesson) " +
                            "VALUES (null, '"+word.getWord()+"', '" + word.getVisualFile() +"', '" +
                            word.getBasicInfo() + "', '" + word.getMoreInfo() + "', '" + word.getLesson() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    //Get all lessons from the specified module
    public ArrayList<Lesson> selectLessonsByModule(String module){
        String sqlQuery = "SELECT lesson_name, module, unlocked, completed, lesson_order FROM " +
                            TABLE_LESSON + " WHERE module = '" + module +"' ORDER BY lesson_order";

        SQLiteDatabase db = this.getWritableDatabase();
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

        SQLiteDatabase db = this.getWritableDatabase();
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
