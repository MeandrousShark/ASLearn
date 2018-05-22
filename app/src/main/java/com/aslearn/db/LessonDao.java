package com.aslearn.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LessonDao {

    @Query("SELECT * FROM lessons WHERE moduleName = :moduleName")
    List<Lesson> getLessonsByModule(String moduleName);


    @Query("SELECT * FROM lessons WHERE moduleName = :moduleName AND unlocked = 1")
    List<Lesson> getUnlockedLessonsByModule(String moduleName);

    @Insert
    void insertLesson(Lesson lesson);

    @Insert
    void insertAll(List<Lesson> lessonList);

    @Delete
    void delete(Lesson lesson);
}
