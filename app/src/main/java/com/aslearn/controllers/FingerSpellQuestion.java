package com.aslearn.controllers;

public class FingerSpellQuestion implements Questionable {

    /**
     * Finds the location of the graphic, can be video or image
     *
     * @return the file location as a String
     */
    @Override
    public String getGraphicFile() {
        return null;
    }

    /**
     * @return the question for the quiz
     */
    @Override
    public String getQuestionText() {
        return null;
    }

    /**
     * Uses the file location to set either an image or a video as the graphic
     */
    @Override
    public void setGraphicFile() {

    }

    /**
     * Sets the text for the question
     */
    @Override
    public void setQuestionText() {

    }

    /**
     * Checks if the user inputted the correct response
     *
     * @return true if correct, false if incorrect
     */
    @Override
    public boolean checkAnswer() {
        return false;
    }
}
