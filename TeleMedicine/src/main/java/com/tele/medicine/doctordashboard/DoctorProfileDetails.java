package com.tele.medicine.doctordashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tele.medicine.R;

/**
 * Created by Database on 7/14/2016.
 */
public class DoctorProfileDetails extends AppCompatActivity implements View.OnClickListener {
    ArrayAdapter<String> mGenderAdapter;
    private Button editProceedButton, saveProceedButton;
    private EditText doctorNameEt,doctorDobEt,mobNumberEt, emailEt,addressEt;
    private Spinner mGenderSp;

    public String genderValue[] = { "Gender", "Male ", "Female "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Profile Details");
        setSupportActionBar(toolbar);

        setUpIds();
        mGenderAdapter = new ArrayAdapter<String>(DoctorProfileDetails.this, R.layout.single_row_spinner, genderValue);
        mGenderAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mGenderSp.setAdapter(mGenderAdapter);

        editProceedButton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void setUpIds()
    {
        doctorNameEt = (EditText)findViewById(R.id.userNameEt);
        doctorDobEt = (EditText)findViewById(R.id.dobEt);
        emailEt = (EditText)findViewById(R.id.input_emailEt);
        addressEt = (EditText)findViewById(R.id.addressEt);
        mobNumberEt = (EditText)findViewById(R.id.mobNumberEt);
        mGenderSp = (Spinner)findViewById(R.id.spGender);
        editProceedButton = (Button)findViewById(R.id.editProceedBtn);
        saveProceedButton = (Button)findViewById(R.id.saveProceedBtn);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.editProceedBtn: {
                editProceedButton.setVisibility(View.GONE);
                saveProceedButton.setVisibility(View.VISIBLE);
                doctorNameEt.setEnabled(true);
                doctorNameEt.setCursorVisible(true);
                doctorDobEt.setEnabled(true);
                emailEt.setEnabled(true);
                addressEt.setEnabled(true);
                mobNumberEt.setEnabled(true);

                saveProceedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
