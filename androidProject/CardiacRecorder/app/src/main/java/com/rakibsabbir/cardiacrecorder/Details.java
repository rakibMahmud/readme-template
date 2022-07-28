package com.rakibsabbir.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {


    Repository repository;
    String dateString;
    String timeString;
    public UserData userData = new UserData();
    String id;

    Button deleteBtn;
    EditText heartRate;
    EditText systolicRate;
    EditText diastoloicRate;
    EditText commentEditText;
    EditText dateEditText;
    EditText timeEditText;

    TextView heartText ;
    TextView systolicText;
    TextView diastolicText;


    Button SaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        deleteBtn = findViewById(R.id.delete_imageBtn);
        heartRate = findViewById(R.id.heart_rate_details);
        systolicRate = findViewById(R.id.systolic_rate_details);
        diastoloicRate = findViewById(R.id.diastolic_rate_details);
        commentEditText = findViewById(R.id.comment_details_text);
        SaveBtn = findViewById(R.id.edit_save);
        dateEditText = findViewById(R.id.date_details);
        timeEditText = findViewById(R.id.time_details);

        heartText = findViewById(R.id.heart_rate_text_details) ;
        systolicText = findViewById(R.id.systolic_rate_text_details) ;
        diastolicText = findViewById(R.id.diastolic_rate_text_details) ;


        userData = (UserData) getIntent().getSerializableExtra("UserData");
        Log.v(id, "Details intent" + userData.getDi());


        // -- database -- //
        repository = new Repository(getApplication());



        heartRate.setText(String.valueOf(userData.getHeartRate()));
        systolicRate.setText(String.valueOf(userData.getSystolic()));
        diastoloicRate.setText(String.valueOf(userData.getDiastolic()));
        commentEditText.setText(userData.getComment());

        dateEditText.setText(String.valueOf(userData.getDate()));
        timeEditText.setText(String.valueOf(userData.getTimestamp()));





        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.delete(userData);
                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String systolicString = systolicRate.getText().toString() ;
                String diastolicString = diastoloicRate.getText().toString() ;
                String heartRateString = heartRate.getText().toString() ;
                String commentString = commentEditText.getText().toString();

                int systolic = 0 , diastolic = 0 , heartRate_int = 0 ;

                if(!systolicString.isEmpty())
                    systolic = Integer.parseInt(systolicString);
                else{
                    systolicRate.setError("Please enter systolic pressure");
                    systolicRate.requestFocus();
                }

                if(!diastolicString.isEmpty())
                    diastolic = Integer.parseInt(diastolicString);
                else
                {
                    diastoloicRate.setError("Please enter diastolic pressure");
                    diastoloicRate.requestFocus();
                }

                if(!heartRateString.isEmpty())
                    heartRate_int = Integer.parseInt(heartRateString);
                else
                {
                    heartRate.setError("Please enter heart rate");
                    heartRate.requestFocus();
                }

                if(dateEditText.getText().toString().isEmpty()){
                    dateEditText.setError("Please enter a date");
                    dateEditText.requestFocus();
                }

                if (timeEditText.getText().toString().isEmpty()) {
                    timeEditText.setError("Please enter a date");
                    timeEditText.requestFocus();
                }


                if (systolic <= 0) {
                    systolicRate.setError("Please enter a valid number");
                    systolicRate.requestFocus();
                }
                if (diastolic <= 0) {
                    diastoloicRate.setError("Please enter a valid number");
                    diastoloicRate.requestFocus();
                }
                if (heartRate_int <= 0) {
                    heartRate.setError("Please enter a valid number");
                    heartRate.requestFocus();
                }

                UserData userData_new = new UserData(dateEditText.getText().toString(), timeEditText.getText().toString(),
                        systolic, diastolic, heartRate_int, commentString);

                userData_new.setDi(userData.getDi());
                repository.update(userData_new);

                Intent intent = new Intent(Details.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


    }


}