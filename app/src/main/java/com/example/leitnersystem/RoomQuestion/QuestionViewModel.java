package com.example.leitnersystem.RoomQuestion;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private String LOGTAG = "QuestionViewModel";

    private QuestionRepository mRepository;

    private LiveData<List<Question>> allQuestions;

    private String mCategory;
    private String mSize;

    // Holds current Category for communication between fragments.
    private MutableLiveData<String> currentCategory = new MutableLiveData<>();

    public QuestionViewModel(Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        allQuestions = mRepository.getAllQuestions();
    }

    public LiveData<List<Question>> findCategory(String genre) {
        return mRepository.findCategory(genre);
    }



    public void setText(String input) {
        Log.d(LOGTAG, "setText = " + input);
        currentCategory.setValue(input);
    }

    public void setSize(String size){
        mSize = size;
    }

    public int getSize(){
        return Integer.parseInt(mSize);
    }

    public void setTextText(String input) {
        mCategory = input;
    }
    public String getTextText() {
        return mCategory;
    }



    public MutableLiveData<String> getCurrentCategory() {
        Log.d(LOGTAG, "getCurrentCategory = " + currentCategory);
        return currentCategory;
    }

    public void insert(Question category){
        mRepository.insert(category);
    }

    public void delete(Question question) {
        mRepository.delete(question);
    }

    public void deleteAllQuestions() {
        mRepository.deleteAllQuestions();
    }

    public void updateQuestion(Question question) {mRepository.updateQuestion(question);}


}
