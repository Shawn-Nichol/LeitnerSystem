package com.example.leitnersystem.RoomQuestion;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Question.class}, version=1)
public abstract class QuestionDatabase extends RoomDatabase {

    private static QuestionDatabase INSTANCE;
    public abstract QuestionDao questionDao();

    public static synchronized  QuestionDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionDatabase.class, "question_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(INSTANCE).execute();
        }

    };

//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private final QuestionDao questionDao;
//
//        public PopulateDbAsyncTask(QuestionDatabase db) {
//            questionDao = db.questionDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            questionDao.insert(new Question("Question 1", "Answer 1", "scifi",1 , 1));
//            questionDao.insert(new Question("Question 2", "Answer 2", "scifi", 1, 1));
//            questionDao.insert(new Question("Question 3", "Answer 3", "scifi", 1, 1));
//            questionDao.insert(new Question("Question 4", "Answer 4", "romance",1, 1));
//            questionDao.insert(new Question("Question 5", "Answer 5", "romance", 1, 1));
//
//            return null;
//        }
//    }

}
