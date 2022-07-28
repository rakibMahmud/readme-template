package com.rakibsabbir.cardiacrecorder;

import android.app.Application;
import android.os.AsyncTask;


import java.util.List;

public class Repository {

    private DataBaseForDisplayingPatientInformation dao;
    private  List<UserData>  allData;

    public Repository(Application application) {
        projectDb database = projectDb.getInstance(application);
        dao = database.Dao();
        allData = dao.getAllData();
    }

    public void insert(UserData model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    public void update(UserData model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    public void delete(UserData model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    public void deleteAllData() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }


    public List<UserData> getAllData() {
        return allData;
    }

    private static class InsertCourseAsyncTask extends AsyncTask<UserData, Void, Void> {
        private DataBaseForDisplayingPatientInformation dao;

        private InsertCourseAsyncTask(DataBaseForDisplayingPatientInformation dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserData... model) {

            dao.InsertData(model[0]);
            return null;
        }
    }


    private static class UpdateCourseAsyncTask extends AsyncTask<UserData, Void, Void> {
        private DataBaseForDisplayingPatientInformation dao;

        private UpdateCourseAsyncTask(DataBaseForDisplayingPatientInformation dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserData... models) {

            dao.UpdateData(models[0]);
            return null;
        }
    }


    private static class DeleteCourseAsyncTask extends AsyncTask<UserData, Void, Void> {
        private DataBaseForDisplayingPatientInformation dao;

        private DeleteCourseAsyncTask(DataBaseForDisplayingPatientInformation dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserData... models) {

            dao.DeleteData(models[0]);
            return null;
        }
    }

    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private DataBaseForDisplayingPatientInformation dao;
        private DeleteAllCoursesAsyncTask(DataBaseForDisplayingPatientInformation dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
