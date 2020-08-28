/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.math_challenge_android;

import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class QuestionGenerator {

    // -------------------------------------
    // Constants
    // -------------------------------------
    public final static char ADDITION = '+';
    public  final static char SUBSTRACTION = '-';
    public  final static char MULTIPLICATION = '*';
    public  final static char DIVISION = '/';
    public  final static char POW = '^';
    public  final static char SQRT = '√';

    // -------------------------------------
    // Atributte
    // -------------------------------------
    private char[] operations;

    // -------------------------------------
    // Constructor
    // -------------------------------------
    public QuestionGenerator(){

        operations = new char[6];
        operations[0] = ADDITION;
        operations[1] = SUBSTRACTION;
        operations[2] = MULTIPLICATION;
        operations[3] = DIVISION;
        operations[4] = POW;
        operations[5] = SQRT;


    }

    // -------------------------------------
    // Methods
    // -------------------------------------
    public Question nextQuestion(){

        int ramdonIndex = (int) (Math.random() * 5) + 0;

        //Log.e("DEBUG",""+ramdonIndex);
        char type = operations[ramdonIndex];
        Log.e("DEBUG",""+type);

        int fisrtTerm = ThreadLocalRandom.current().nextInt(1, 20 + 1);

        int secondTerm = ThreadLocalRandom.current().nextInt(1, 20 + 1);
        int answer = 0;

        switch (type) {

            case ADDITION:
                answer = fisrtTerm + secondTerm;
                break;

            case SUBSTRACTION:
                answer = fisrtTerm - secondTerm;
                break;

            case MULTIPLICATION:
                answer = fisrtTerm * secondTerm;
                break;

            case DIVISION:

                secondTerm = ThreadLocalRandom.current().nextInt(2, 3 + 1)*fisrtTerm;
                int temp = fisrtTerm;
                fisrtTerm = secondTerm;
                secondTerm = temp;
                answer = fisrtTerm / secondTerm;

                break;


            case POW:

                secondTerm = ThreadLocalRandom.current().nextInt(2, 4 + 1);
                answer = (int)(Math.pow(fisrtTerm, secondTerm));

                break;

            case SQRT:

                secondTerm = 0;
                answer = fisrtTerm;
                fisrtTerm *= fisrtTerm;

                break;

        }

        return new Question(fisrtTerm, secondTerm, answer, type);

    }


}
