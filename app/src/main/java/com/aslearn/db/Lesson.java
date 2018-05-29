package com.aslearn.db;

//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.PrimaryKey;
//import android.support.annotation.NonNull;
//
//@Entity(tableName = "lessons")
public class Lesson {

//    @PrimaryKey @NonNull
    private String lessonName;

  //  @ColumnInfo(name="moduleName")
    private String moduleName;

   // @ColumnInfo(name="unlocked")
    private int unlocked;

   // @ColumnInfo(name="completed")
    private int completed;

  //  @ColumnInfo(name="nextLesson")
    private int lessonOrder;

    //Constructors
   // @Ignore
//    public Lesson(String lessonName, String moduleName, String nextLesson){
//        this.lessonName=lessonName;
//        this.moduleName=moduleName;
//        this.completed=false;
//        this.unlocked=false;
//        this.nextLesson=nextLesson;
//    }

    public Lesson(String lessonName, String moduleName, int lessonOrder, int unlocked){
        this.lessonName=lessonName;
        this.moduleName=moduleName;
        this.completed=0;
        this.unlocked=unlocked;
        this.lessonOrder=lessonOrder;
    }

    //Getters and Setters
    public String getLessonName(){
        return this.lessonName;
    }

    public void setLessonName(String lesson){
        this.lessonName=lesson;
    }

    public String getModuleName(){
        return this.moduleName;
    }

    public void setModuleName(String module){
        this.moduleName=module;
    }

    public int getUnlocked(){
        return this.unlocked;
    }

    public void setUnlocked(int unlocked){
        this.unlocked=unlocked;
    }

    public int getCompleted() {
        return this.completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
//        if (this.nextLesson != null){
//            this.unlocked=true;
//        }
    }

    public int getLessonOrder() {
        return this.lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }
}
