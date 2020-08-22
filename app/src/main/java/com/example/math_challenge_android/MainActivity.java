package com.example.math_challenge_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button check;
    private TextView question;
    private EditText answer;
    private TextView score;
    private Question currentQuestion;
    private QuestionGenerator questionGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check = findViewById(R.id.check);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        score = findViewById(R.id.score);

        questionGenerator = new QuestionGenerator();
        currentQuestion = questionGenerator.nextQuestion();

        question.setText(formatQuestion(currentQuestion));


    }

    private String formatQuestion(Question question){

        if(question.getOperation() == QuestionGenerator.SQRT)
            return ""+question.getOperation()+" "+question.getFirstTerm();
        return ""+question.getFirstTerm()+" "+question.getOperation()+" "+question.getSecondTerm();

    }
}