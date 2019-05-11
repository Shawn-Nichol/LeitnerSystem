package com.example.leitnersystem.RoomCategory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * This is the backend of the title database.
 */
@Database(entities = {Category.class}, version = 1)
public abstract class CategoryRoomDatabase extends RoomDatabase {

    // Define the DAO that works with the title db.
    public abstract CategoryDao CategoryDao();

    // Make db a singleton to prevent multiple instances of the db being open at once.
    private static volatile CategoryRoomDatabase INSTANCE;

    public static CategoryRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (CategoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryRoomDatabase.class, "title_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
