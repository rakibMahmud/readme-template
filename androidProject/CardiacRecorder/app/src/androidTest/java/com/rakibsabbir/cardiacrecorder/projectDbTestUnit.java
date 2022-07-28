package com.rakibsabbir.cardiacrecorder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class projectDbTestUnit {
    private projectDb db;
    private DataBaseForDisplayingPatientInformation dao;
 

    @Before
    public void before() {

        db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                projectDb.class
        ).build();
        dao = db.Dao();

    }

    @Test
    public void LoadData() throws Exception{


        int prevSize = dao.getAllData().size();

        UserData userData = new UserData("28/07/22", "12.00",
                100, 100, 100, "Testing1");
        userData.setDi(System.currentTimeMillis());

        dao.InsertData(userData);

        UserData userData1 = new UserData("29/07/22", "12.00",
                60, 60, 60, "Testing2");
        userData1.setDi((System.currentTimeMillis())+100);

        dao.InsertData(userData1);

        UserData userData2 = new UserData("30/07/22", "12.00",
                77, 77, 77, "Testing3 ");
        userData2.setDi(System.currentTimeMillis()+200);

        dao.InsertData(userData2);

        int curSize = dao.getAllData().size();

        assertEquals(prevSize+3,curSize);



    }


    @Test
    public void DataInsertingTest() throws Exception{


        UserData userData = new UserData("28/07/22", "12.00",
                100, 100, 100, "Testing");
        userData.setDi(System.currentTimeMillis());

        dao.InsertData(userData);
        UserData allData = dao.getData(String.valueOf(userData.getDi()));
        assertEquals(allData.getDi(),userData.getDi());



    }



    @Test
    public void DataDeletingTest() throws Exception{


        UserData userData = new UserData("28/07/22", "12.00",
                100, 100, 100, "Testing");
        userData.setDi(System.currentTimeMillis());

        dao.InsertData(userData);
        dao.DeleteData(userData);

        UserData userDataNUll = dao.getData(String.valueOf(userData.getDi())) ;

        assertNull(userDataNUll);

    }
    @Test
    public void DataUpadatingTest() throws Exception{


        UserData userData = new UserData("28/07/22", "12.00",
                100, 100, 100, "Testing");
        userData.setDi(System.currentTimeMillis());

        dao.InsertData(userData);


        UserData userDataRetrive = dao.getData(String.valueOf(userData.getDi())) ;
        userDataRetrive.setDiastolic(70);
        dao.UpdateData(userDataRetrive);

        UserData userDataUpdate = dao.getData(String.valueOf(userData.getDi())) ;

        assertEquals(userDataUpdate.getDiastolic(),70);




    }



    @After
    public void functionFinishing() {
        db.close();
    }
}