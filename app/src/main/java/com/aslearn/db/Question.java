package com.aslearn.db;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {
    private int questionID;
    private String question;
    private String answer;
    private String lesson;
    private String relatedWords; //This is in the form of a list of words separated by commas

    private String type;
    private String wrongAnswers;

    //Constructor
    public Question (int questionID, String question, String answer, String lesson, String relatedWords, String type, String wrongAnswers){
        this.questionID=questionID;
        this.question=question;
        this.answer=answer;
        this.lesson=lesson;
        this.relatedWords=relatedWords;

        this.type=type;
        this.wrongAnswers= wrongAnswers;
    }

    //Getters and Setters
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuestionID(){
        return questionID;
    }

    public void setQuestion(String question){
        this.question=question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer(){
        return answer;
    }

    public void setLesson(String lesson){
        this.lesson=lesson;
    }

    public String getLesson(){
        return lesson;
    }

    public void setRelatedWords(String relatedWords){
        this.relatedWords=relatedWords;
    }

    public String getRelatedWords(){
        return relatedWords;
    }

    public ArrayList<String> getRelatedWordsAsList(){
        String[] list = relatedWords.split(",");
        return new ArrayList<>(Arrays.asList(list));
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setWrongAnswers(String wrongAnswers){
        this.wrongAnswers=wrongAnswers;
    }

    public String getWrongAnswers(){ return wrongAnswers;}

    public ArrayList<String>getWrongAnswersAsList(){
        String[] list = wrongAnswers.split(",");
        return new ArrayList<>(Arrays.asList(list));
    }
}
