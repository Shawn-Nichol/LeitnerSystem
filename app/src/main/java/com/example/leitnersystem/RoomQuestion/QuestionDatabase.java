package com.example.leitnersystem.RoomQuestion;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Question.class}, version=1)
public abstract class QuestionDatabase extends RoomDatabase {

    private static QuestionDatabase INSTANCE;
    public abstract QuestionDao questionDao();

    public static synchronized  QuestionDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionDatabase.class, "question_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
