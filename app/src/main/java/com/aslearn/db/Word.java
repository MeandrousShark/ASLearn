package com.aslearn.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "words", foreignKeys = @ForeignKey(entity = Lesson.class,
                                                        parentColumns = "lessonName",
                                                        childColumns = "lesson"))
public class Word {
    @PrimaryKey (autoGenerate = true)
    private int word_id;

    @ColumnInfo(name="word")
    private String word;

    @ColumnInfo (name="visual_file")
    private String visualFile;

    @ColumnInfo (name = "basic_info")
    private String basicInfo;

    @ColumnInfo (name = "more_info")
    private String moreInfo;

    @ColumnInfo (name="lesson")
    private String lesson;

    private int fluencyVal;

    //Constructors
    private Word (String word, String visualFile, String basicInfo, String moreInfo, String lesson){
        this.word=word;
        this.visualFile=visualFile;
        this.basicInfo=basicInfo;
        this.moreInfo=moreInfo;
        this.lesson=lesson;
        this.fluencyVal=0;
    }

    //Getters and Setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getVisualFile() {
        return visualFile;
    }

    public void setVisualFile(String visualFile) {
        this.visualFile = visualFile;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getFluencyVal() {
        return fluencyVal;
    }

    public void setFluencyVal(int fluencyVal) {
        this.fluencyVal = fluencyVal;
    }
}
