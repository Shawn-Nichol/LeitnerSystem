package com.example.leitnersystem.RoomQuestion;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "questions_table")
public class Question {

    @PrimaryKey(autoGenerate =  true)
    private int id;

    private final String question;
    private final String answer;
    private final String category;
    private final int box;
    private final int counter;


    public Question(String question, String answer, String category, int box, int counter) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.box = box;
        this.counter = counter;
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

    public int getBox() {
        return box;
    }

    public int getCounter() {
        return counter;
    }
}
