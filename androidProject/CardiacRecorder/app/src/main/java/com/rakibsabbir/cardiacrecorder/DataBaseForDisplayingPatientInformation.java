package com.rakibsabbir.cardiacrecorder;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface DataBaseForDisplayingPatientInformation {

    @Insert
    void InsertData(UserData userData);

    @Delete
    void DeleteData(UserData userData);

    @Update
    void UpdateData(UserData userData);


    @Query("SELECT * FROM USER_DATA WHERE di = :id")
    UserData getData(String id);


    @Query("SELECT * FROM User_Data")
    List<UserData> getAllData();



}
