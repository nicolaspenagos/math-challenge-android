/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.math_challenge_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // -------------------------------------
    // XML references
    // -------------------------------------
    private Button check;
    private Button tryAgain;
    private TextView question;
    private TextView timer;
    private EditText answer;
    private TextView score;
    private Question currentQuestion;
    private QuestionGenerator questionGenerator;
    private ConstraintLayout mainLayout;

    // -------------------------------------
    // Global variables
    // -------------------------------------
    private int scoreCounter;
    private int sec;


    // -------------------------------------
    // Gui methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreCounter = 0;

        check = findViewById(R.id.check);
        tryAgain = findViewById(R.id.tryAgain);
        question = findViewById(R.id.question);
        timer = findViewById(R.id.timer);
        answer = findViewById(R.id.answer);
        score = findViewById(R.id.score);
        mainLayout = findViewById(R.id.mainLayout);
        questionGenerator = new QuestionGenerator();
        currentQuestion = questionGenerator.nextQuestion();

        question.setText(formatQuestion(currentQuestion));
        tryAgain.setVisibility(View.GONE);
        startTimer();


        check.setOnClickListener(

                (v)->{

                    if(currentQuestion.getAnswer() == Integer.parseInt(String.valueOf(answer.getText())) ){

                        feedback(true);
                        scoreCounter++;
                        score.setText("Score: " + scoreCounter);

                    }else{
                        feedback(false);
                    }

                    answer.setText("");
                    currentQuestion = questionGenerator.nextQuestion();
                    question.setText(formatQuestion(currentQuestion));

                }

        );

        tryAgain.setOnClickListener(

                (v) -> {

                    scoreCounter = 0;
                    score.setText("Score: " + scoreCounter);
                    startTimer();


                    answer.getBackground().setAlpha(255);
                    answer.setEnabled(true);

                    check.getBackground().setAlpha(255);
                    check.setEnabled(true);

                    tryAgain.setVisibility(View.GONE);



                }

        );


    }

    private String formatQuestion(Question question){

        if(question.getOperation() == QuestionGenerator.SQRT)
            return ""+question.getOperation()+" "+question.getFirstTerm();
        return ""+question.getFirstTerm()+" "+question.getOperation()+" "+question.getSecondTerm();

    }

    public void startTimer(){

        new Thread(

                ()->{
                    runOnUiThread(
                            ()-> {
                               timer.setTextColor(Color.rgb(120, 119, 119));
                               score.setTextColor(Color.rgb(120, 119, 119));
                            }
                    );

                    sec = 30;
                    while(sec>=0){

                        runOnUiThread( ()-> timer.setText(""+sec+" s"));

                        if(sec==10)
                            runOnUiThread( ()-> timer.setTextColor(Color.rgb(255, 0, 0 )));

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        sec--;
                    }

                    runOnUiThread(()->{

                        tryAgain.setVisibility(View.VISIBLE);

                        answer.getBackground().setAlpha(64);
                        answer.setEnabled(false);

                        check.getBackground().setAlpha(64);
                        check.setEnabled(false);

                        score.setTextColor(Color.rgb(0, 128, 0));

                    });

                }

        ).start();

    }

    public void feedback(boolean correct){

        new Thread(

                () -> {

                    if(correct){
                       runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(132,250,103)));
                    }else{
                        runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(232,46,83)));
                    }

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(249,234,59)));

                }

        ).start();

    }

}