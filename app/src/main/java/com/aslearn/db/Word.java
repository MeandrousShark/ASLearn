package com.aslearn.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {
    @PrimaryKey (autoGenerate = true)
    private int word_id;



    @ColumnInfo(name="word")
    private String word;

    @ColumnInfo (name="visual_file")
    private String visual_file;

    @ColumnInfo (name = "basic_info")
    private String basic_info;

    @ColumnInfo (name = "more_info")
    private String more_info;


}
