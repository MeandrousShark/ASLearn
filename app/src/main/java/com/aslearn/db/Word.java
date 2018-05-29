package com.aslearn.db;

//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.ForeignKey;
//import android.arch.persistence.room.PrimaryKey;

//@Entity(tableName = "words", foreignKeys = @ForeignKey(entity = Lesson.class,
//                                                        parentColumns = "lessonName",
//                                                        childColumns = "lesson"))
public class Word {
  //  @PrimaryKey (autoGenerate = true)
    private int word_id;

    //@ColumnInfo(name="word")
    private String word;

   // @ColumnInfo (name="visual_file")
    private String visualFile;

  //  @ColumnInfo (name = "basic_info")
    private String basicInfo;

   // @ColumnInfo (name = "more_info")
    private String moreInfo;

  //  @ColumnInfo (name="lesson")
    private int lesson_id;

    private int fluencyVal;

    //Constructors
    Word (int word_id, String word, String visualFile, String basicInfo, String moreInfo, int lesson, int fluencyVal){
        this.word_id = word_id;
        this.word=word;
        this.visualFile=visualFile;
        this.basicInfo=basicInfo;
        this.moreInfo=moreInfo;
        this.lesson_id=lesson;
        this.fluencyVal=fluencyVal;
    }

    //Getters and Setters
    public int getWord_id() {return word_id;}

    public void setWord_id(int word_id) { this.word_id = word_id; }

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

    public int getLesson() {
        return lesson_id;
    }

    public void setLesson(int lesson) {
        this.lesson_id = lesson;
    }

    public int getFluencyVal() {
        return fluencyVal;
    }

    public void setFluencyVal(int fluencyVal) {
        this.fluencyVal = fluencyVal;
    }
}
