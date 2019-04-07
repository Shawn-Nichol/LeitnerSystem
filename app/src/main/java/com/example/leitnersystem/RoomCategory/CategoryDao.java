package com.example.leitnersystem.RoomCategory;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * The Room Magic is in this file, where you map a Java method call to the SQL query.
 */
@Dao
public interface CategoryDao {

    // Declare method to get all the titles and return them in a list
    @Query("SELECT * FROM title_table ORDER BY title ASC")
    LiveData<List<Category>> getAlphabetizedTitle();

    // Declare method to insert a category
    @Insert
    void insert(Category category);

    // Declare a method to delete all the titles,
    @Query("DELETE FROM title_table")
    void deleteAll();


}
