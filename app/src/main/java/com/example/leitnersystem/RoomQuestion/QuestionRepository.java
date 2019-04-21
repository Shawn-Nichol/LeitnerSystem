package com.example.leitnersystem.RoomQuestion;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class QuestionRepository {

    private final QuestionDao questionDao;
    private final LiveData<List<Question>> allQuestions;

    // Constructor
    public QuestionRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getInstance(application);
        questionDao = db.questionDao();
        allQuestions = questionDao.getAllQuestions();
    }


    public void insert(Question question) {
        new InsertAsyncTask(questionDao).execute(question);
    }

    public void delete(Question question){
        new DeleteAsyncTask(questionDao).execute(question);
    }

    public void deleteAllQuestions() {
        new DeleteAllAsyncTask(questionDao).execute();
    }

    public void updateQuestion(Question question) {
        new UpdateAsyncTask(questionDao).execute(question);
    }





    public LiveData<List<Question>> getAllQuestions(){
        return allQuestions;
    }

    public LiveData<List<Question>> findCategory(String category) {
        return questionDao.findCategory(category);
    }


    private static class InsertAsyncTask extends AsyncTask<Question, Void, Void> {
        private final QuestionDao mAsyncTaskDao;

        InsertAsyncTask(QuestionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Question, Void, Void> {
        private final QuestionDao mAsyncTaskDao;

        DeleteAsyncTask(QuestionDao dao) {

            this.mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.deleteQuestion(params[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private final QuestionDao mAsyncTaskDao;

        DeleteAllAsyncTask(QuestionDao dao) {

            this.mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAllQuestions();
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Question, Void, Void> {
        private final QuestionDao mAsyncTaskDao;

        UpdateAsyncTask(QuestionDao dao) {

            this.mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.updateQuestion(params[0]);
            return null;
        }
    }


}
