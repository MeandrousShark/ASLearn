package com.aslearn;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
/**
 *
 * The Java Class that handles multiple choice questions. This is where
 * all of the code should be for it (or subclasses)
 */

public class MultipleChoice extends AppCompatActivity{
    TextView MCQuestion;
    VideoView MultipleChoiceVid;
    Button MCFirstGuess;
    Button MCSecondGuess;
    Button MCThirdGuess;
    Button MCFourthGuess;
    Button MCConfirmButton;
    Boolean Correct; //Whether or not they got the question right

}
