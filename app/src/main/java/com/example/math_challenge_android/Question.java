/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.math_challenge_android;

public class Question {

    // -------------------------------------
    // Atributtes
    // -------------------------------------
    private int firstTerm;
    private int secondTerm;
    private double answer;
    private char operation;

    // -------------------------------------
    // Constructor
    // -------------------------------------
    public Question(int firstTerm, int secondTerm, int answer, char operation){

        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.answer = answer;
        this.operation = operation;

    }

    // -------------------------------------
    // Getters and Setters
    // -------------------------------------
    public int getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(int firstTerm) {
        this.firstTerm = firstTerm;
    }

    public int getSecondTerm() {
        return secondTerm;
    }

    public void setSecondTerm(int secondTerm) {
        this.secondTerm = secondTerm;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

}
