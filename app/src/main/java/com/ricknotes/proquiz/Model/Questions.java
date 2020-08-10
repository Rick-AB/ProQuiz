package com.ricknotes.proquiz.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {
    private String category;
    private String type;
    private String difficulty;
    private String question ;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswer;

    public Questions(String category, String type, String difficulty, String question, String correctAnswer, List<String> incorrectAnswer) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    public Questions() {
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswer() {
        return incorrectAnswer;
    }
}
