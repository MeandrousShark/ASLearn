package com.aslearn.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

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
        String createLessons = "CREATE TABLE `Lessons` ( `lesson_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                                        "`module` TEXT NOT NULL, "+
                                                        "`lesson_name` TEXT NOT NULL, "+
                                                        "`unlocked` INTEGER NOT NULL DEFAULT 0, " +
                                                        "`completed` INTEGER NOT NULL DEFAULT 0, " +
                                                        "`lesson_order` INTEGER NOT NULL )";
        String createWords = "CREATE TABLE `Words` ( `word_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                                    "`word` TEXT NOT NULL, `visual_file` TEXT NOT NULL, " +
                                                    "`basic_info` TEXT NOT NULL, " +
                                                    "`more_info` TEXT, " +
                                                    "`lesson_id` INTEGER NOT NULL, " +
                                                    "`fluency` INTEGER NOT NULL DEFAULT 0, " +
                                                    "FOREIGN KEY(`lesson_id`) REFERENCES `Lessons`(`lesson_id`) )";
        db.execSQL(createLessons);
        db.execSQL(createWords);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        onCreate(db);
    }

    public void insertLesson(Lesson lesson){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "INSERT INTO " + TABLE_LESSON + " (module, lesson_name, unlocked, lesson_order)" +
                            "VALUES ('"+ lesson.getModuleName()+"', '"+ lesson.getLessonName() +"', "+lesson.getUnlocked()+", "+ lesson.getLessonOrder()+")";
    };
}
