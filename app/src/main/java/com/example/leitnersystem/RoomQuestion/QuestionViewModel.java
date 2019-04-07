package com.example.leitnersystem.RoomQuestion;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestions;

    public QuestionViewModel(Application application) {
        super(application);
        repository = new QuestionRepository(application);
        allQuestions = repository.getAllQuestions();
    }

    public LiveData<List<Question>> findCategory(String genre) {
        return repository.findCategory(genre);
    }
}
