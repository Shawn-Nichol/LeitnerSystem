package com.example.leitnersystem.RoomCategory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * View model keeps a reference to the Category repository and an up to date list of all categories.
 */
public class CategoryViewModel extends AndroidViewModel {

    // Member variable for DAO and the list of titles
    private final CategoryRepository mRepository;
    private final LiveData<List<Category>> mAllCategories;

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
        mRepository.insert(category);
    }

    public void delete(Category category) {
        mRepository.delete(category);
    }
}
