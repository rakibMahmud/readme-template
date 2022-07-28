package com.rakibsabbir.cardiacrecorder;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserData.class}, version = 3)
public abstract class projectDb extends RoomDatabase {


    private static projectDb instance;


    public abstract DataBaseForDisplayingPatientInformation Dao();


    public static synchronized projectDb getInstance(Context context) {

        if (instance == null) {

            instance =

                    Room.databaseBuilder(context.getApplicationContext(),
                            projectDb.class, "User_Data")

                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()

                            .addCallback(roomCallback)

                            .build();
        }

        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(projectDb instance) {
            DataBaseForDisplayingPatientInformation dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}