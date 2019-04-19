package com.example.leitnersystem.RoomCategory;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "title_table")
public class Category {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private final String mTitle;

    // Constructor
    public Category(@NonNull String title) {
        this.mTitle = title;
    }

    // Getter for the data model
    public String getTitle() {
        return this.mTitle;
    }
}
