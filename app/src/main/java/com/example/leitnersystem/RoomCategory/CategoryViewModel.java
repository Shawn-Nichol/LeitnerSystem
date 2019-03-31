package com.example.leitnersystem.RoomCategory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

/**
 * View model keeps a reference to the word repository and an up to date list of all words.
 */
public class CategoryViewModel extends AndroidViewModel {

    // Member variable to hold reference to UI
    private CategoryRepository mRepository;

    // Member variable LiveData
    private LiveData<List<Category>> mAllCategories;

    // Constructor gets a reference to the repository and a list of Titles from the repository.
    public CategoryViewModel(Application application) {
        super(application);
        mRepository = new CategoryRepository(application);
        mAllCategories = mRepository.getAllTitles();
    }

    // Getter method hides the implementation of all the titles from the UI
    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    // Wrapper insert() method that calls the repository insert.
    public void insert(Category category) {
        Log.d("Shawn", category.toString());
        mRepository.insert(category);
    }

}
