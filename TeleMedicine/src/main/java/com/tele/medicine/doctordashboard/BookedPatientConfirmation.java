package com.tele.medicine.doctordashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.R;

/**
 * Created by Database on 7/15/2016.
 */
public class BookedPatientConfirmation extends AppCompatActivity implements View.OnClickListener {
    String patientName, patientAge, bookedDateTime, patientCity;
    private TextView patientNameTv, patientAgeTv, patientCityTv, bookDateTimeTv;
    private Button confirmationProceedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_patient_confirmation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Confirmation");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            patientName = bundle.getString("PATIENT_NAME");
            patientAge = bundle.getString("PATIENT_AGE");
            bookedDateTime = bundle.getString("PATIENT_DATE_TIME");
            patientCity = bundle.getString("PATIENT_CITY");
        }

        setUpIds();
        confirmationProceedBtn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpIds()
    {
        patientNameTv = (TextView)findViewById(R.id.patientNameTv);
        patientAgeTv = (TextView)findViewById(R.id.ageTv);
        bookDateTimeTv = (TextView)findViewById(R.id.dateTimeTv);
        patientCityTv = (TextView)findViewById(R.id.cityTv);
        confirmationProceedBtn = (Button)findViewById(R.id.signUpPreceedBtn);

        patientNameTv.setText(patientName);
        patientAgeTv.setText(patientAge);
        bookDateTimeTv.setText(bookedDateTime);
        patientCityTv.setText(patientCity);
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(BookedPatientConfirmation.this, "Confirm ", Toast.LENGTH_SHORT).show();
        finish();
    }
}
