package com.aslearn.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM words WHERE lesson = :lesson")
    List<Word> getWordsByLesson(String lesson);

    @Query("SELECT * FROM words WHERE word LIKE :word")
    List<Word> findWord(String word);

    @Insert
    void insertWord(Word word);

    @Insert
    void insertAll(List<Word> wordList);

    @Delete
    void delete(Word word);

}
