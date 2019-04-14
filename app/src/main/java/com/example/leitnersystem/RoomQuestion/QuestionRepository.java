package com.example.leitnersystem.RoomQuestion;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class QuestionRepository {

    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;

    public void insert(Question category) {
        new InsertAsyncTask(questionDao).execute(category);
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



    public QuestionRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getInstance(application);
        questionDao = db.questionDao();
        allQuestions = questionDao.getAllQuestions();
    }

    public LiveData<List<Question>> getAllQuestions(){
        return allQuestions;
    }

    public LiveData<List<Question>> findCategory(String category) {
        return questionDao.findCategory(category);
    }


    private static class InsertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDao mAsyncTaskDao;

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
        private QuestionDao mAsyncTaskDao;

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
        private QuestionDao mAsyncTaskDao;

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
        private QuestionDao mAsyncTaskDao;

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
