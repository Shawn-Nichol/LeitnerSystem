package com.example.leitnersystem.RoomQuestion;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;

    public QuestionRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getInstance(application);
        questionDao = db.questionDao();
        allQuestions = questionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions(){
        return allQuestions;
    }

    public LiveData<List<Question>> findCategory(String category) {
        return questionDao.findCategory(category);
    }
}
