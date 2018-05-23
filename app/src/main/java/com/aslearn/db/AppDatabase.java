package com.aslearn.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities= {Word.class, Lesson.class}, version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;

    public abstract WordDao wordDao();
    public abstract LessonDao lessonDao();

    public synchronized static AppDatabase getInstance(Context context){
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "appDB").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getInstance(context).lessonDao().insertLesson(new Lesson("A-G",
                                "A","H-N", true));
                        getInstance(context).wordDao().insertWord(new Word("A", "a.jpg",
                                "basic info for a", "more info for a", "A-G"));
                    }
                });
            }
        }).build();
    }
}
