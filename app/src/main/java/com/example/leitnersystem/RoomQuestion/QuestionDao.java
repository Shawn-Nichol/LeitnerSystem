package com.example.leitnersystem.RoomQuestion;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(Question question);

    @Query("SELECT * FROM questions_table")
    LiveData<List<Question>> getAllQuestions();

    @Query("SELECT * FROM questions_table WHERE category = :category")
    LiveData<List<Question>> findCategory(String category);
}
