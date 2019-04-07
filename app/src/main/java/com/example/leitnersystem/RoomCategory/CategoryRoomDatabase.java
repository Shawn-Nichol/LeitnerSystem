package com.example.leitnersystem.RoomCategory;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;



/**
 * This is the backend of the title database.
 */
@Database(entities = {Category.class}, version = 1)
public abstract class CategoryRoomDatabase extends RoomDatabase {

    // Define the DAO that works with the title db.
    public abstract CategoryDao CategoryDao();

    // Make db a singleton to prevent multiple instances of the db being open at once.
    private static volatile CategoryRoomDatabase INSTANCE;

    static CategoryRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (CategoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryRoomDatabase.class, "title_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the db.
     *
     * populate the db only when the db is created the 1st time, Override RoomDatabase.CallBack()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // TODO: test data for db, remove when done
          //  new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CategoryDao mDao;

        PopulateDbAsync(CategoryRoomDatabase db) {
            mDao = db.CategoryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            Category category = new Category("Hello");
            mDao.insert(category);

            category = new Category("World");
            mDao.insert(category);
            return null;
        }
    }
}
