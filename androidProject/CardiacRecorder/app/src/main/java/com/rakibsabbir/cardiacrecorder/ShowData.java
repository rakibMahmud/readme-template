package com.rakibsabbir.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShowData extends AppCompatActivity {


    Repository repository;
    String dateString;
    String timeString;


    // ---- UI VARIABLES --- //
    EditText dateEdit;
    EditText timeEdit;
    EditText systolicEdit;
    EditText diastolicEdit;
    EditText heartRateEdit;
    EditText commentEdit;
    Button saveBtn;
    // -------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        // --------- FIND VIEW BY ID ------------- //
        dateEdit = findViewById(R.id.date_edit_text_id);
        timeEdit = findViewById(R.id.time_edit_text);
        heartRateEdit = findViewById(R.id.heartRate_edit_text);
        systolicEdit = findViewById(R.id.systolic_edit_text);
        diastolicEdit = findViewById(R.id.diastolic_edit_text);
        commentEdit = findViewById(R.id.comment_edit_text);
        saveBtn = findViewById(R.id.save_button);

        // ----------------------------------------------------- //



        repository = new Repository(getApplication());




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String systolicString = systolicEdit.getText().toString();
                String diastolicString = diastolicEdit.getText().toString();
                String heartRateString = heartRateEdit.getText().toString();
                String commentString = commentEdit.getText().toString();



                int systolic = 0, diastolic = 0, heartRate = 0;

                if (!systolicString.isEmpty()) {
                    systolic = Integer.parseInt(systolicString);

                    if (!diastolicString.isEmpty()) {
                        diastolic = Integer.parseInt(diastolicString);

                        if (!heartRateString.isEmpty()) {
                            heartRate = Integer.parseInt(heartRateString);
                            if (dateEdit.getText().toString().isEmpty()) {
                                dateEdit.setError("Please enter a date");
                                dateEdit.requestFocus();
                            } else {
                                if (timeEdit.getText().toString().isEmpty()) {
                                    timeEdit.setError("Please enter a date");
                                    timeEdit.requestFocus();
                                } else {

                                    if (systolic <= 0) {
                                        systolicEdit.setError("Please enter a valid number");
                                        systolicEdit.requestFocus();
                                    } else {
                                        if (diastolic <= 0) {
                                            diastolicEdit.setError("Please enter a valid number");
                                            diastolicEdit.requestFocus();
                                        } else {
                                            if (heartRate <= 0) {
                                                heartRateEdit.setError("Please enter a valid number");
                                                heartRateEdit.requestFocus();
                                            } else {
                                                UserData userData = new UserData(dateEdit.getText().toString(), timeEdit.getText().toString(),
                                                        systolic, diastolic, heartRate, commentString);

                                                userData.setDi(System.currentTimeMillis());
                                                repository.insert(userData);

                                                Intent intent = new Intent(ShowData.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }
                                    }


                                }
                            }
                        } else {
                            heartRateEdit.setError("Please enter heart rate");
                            heartRateEdit.requestFocus();
                        }
                    } else {
                        diastolicEdit.setError("Please enter diastolic pressure");
                        diastolicEdit.requestFocus();
                    }
                } else {
                    systolicEdit.setError("Please enter systolic pressure");
                    systolicEdit.requestFocus();
                }


            }
        });

    }


}