package com.aslearn.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "lessons")
public class Lesson {

    @PrimaryKey @NonNull
    private String lessonName;

    @ColumnInfo(name="moduleName")
    private String moduleName;

    @ColumnInfo(name="unlocked")
    private boolean unlocked;

    @ColumnInfo(name="completed")
    private boolean completed;

    @ColumnInfo(name="nextLesson")
    private String nextLesson;

    //Constructors
    @Ignore
    public Lesson(String lessonName, String moduleName, String nextLesson){
        this.lessonName=lessonName;
        this.moduleName=moduleName;
        this.completed=false;
        this.unlocked=false;
        this.nextLesson=nextLesson;
    }

    public Lesson(String lessonName, String moduleName, String nextLesson, boolean unlocked){
        this.lessonName=lessonName;
        this.moduleName=moduleName;
        this.completed=false;
        this.unlocked=unlocked;
        this.nextLesson=nextLesson;
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

    public boolean isUnlocked(){
        return this.unlocked;
    }

    public void setUnlocked(boolean unlocked){
        this.unlocked=unlocked;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (this.nextLesson != null){
            this.unlocked=true;
        }
    }

    public String getNextLesson() {
        return this.nextLesson;
    }

    public void setNextLesson(String nextLesson) {
        this.nextLesson = nextLesson;
    }
}
