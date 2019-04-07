package com.example.leitnersystem.RoomQuestion;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "questions_table")
public class Question {

    @PrimaryKey(autoGenerate =  true)
    private int id;

    private String question;
    private String answer;
    private String category;

    public Question(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCategory() {
        return category;
    }
}
