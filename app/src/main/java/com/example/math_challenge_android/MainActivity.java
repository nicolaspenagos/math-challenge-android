package com.example.math_challenge_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button check;
    private Button tryAgain;
    private TextView question;
    private TextView timer;
    private EditText answer;
    private TextView score;
    private Question currentQuestion;
    private QuestionGenerator questionGenerator;
    private ConstraintLayout mainLayout;

    private int scoreCounter;
    private int sec;


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
                        score.setText("Socore: " + scoreCounter);

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
                    score.setText("Socore: " + scoreCounter);
                    startTimer();


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

                    sec = 30;
                    while(sec>=0){

                        runOnUiThread( ()-> timer.setText(""+sec+" s"));

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        sec--;
                    }

                    runOnUiThread(()->{

                        tryAgain.setVisibility(View.VISIBLE);



                    });

                }

        ).start();

    }

    public void feedback(boolean correct){

        new Thread(

                () -> {

                    if(correct){
                       runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(202,220,195)));
                    }else{
                        runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(213,90,97)));
                    }

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(() ->  mainLayout.setBackgroundColor(Color.rgb(255,255,255)));

                }

        ).start();


    }

}