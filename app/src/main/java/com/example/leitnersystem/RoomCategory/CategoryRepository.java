package com.example.leitnersystem.RoomCategory;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CategoryRepository {

    // Member variables for the DAO and the list of words
    private CategoryDao mCategoryDao;
    private LiveData<List<Category>> mAllTitles;

    // Constructor
    CategoryRepository(Application application) {
        CategoryRoomDatabase db = CategoryRoomDatabase.getDatabase(application);
        mCategoryDao = db.titleDao();
        mAllTitles = mCategoryDao.getAlphabetizedTitle();
    }

    // Room executes all queries on a separate thread, Observed LiveData will notify the observer
    // when data is changed.
    LiveData<List<Category>> getAllTitles() {
        return mAllTitles;
    }


    public void insert(Category category) {
        new insertAsyncTask(mCategoryDao).execute(category);
    }

    // Call on a non UI thread.
    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao mAsyncTaskDao;

        // Constructor
        insertAsyncTask(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
