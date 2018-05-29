package com.aslearn.controllers;

/**
 * The base methods for all of the question classes
 */

public interface Questionable {

    /**
     * Finds the location of the graphic, can be video or image
     * @return the file location as a String
     */
    String getGraphicFile();

    /**
     *
     * @return the question for the quiz
     */
    String getQuestionText();

    /**
     * Uses the file location to set either an image or a video as the graphic
     */
    void setGraphicFile();

    /**
     * Sets the text for the question
     */
    void setQuestionText();

    /**
     * Checks if the user inputted the correct response
     * @return true if correct, false if incorrect
     */
    boolean checkAnswer();



}
