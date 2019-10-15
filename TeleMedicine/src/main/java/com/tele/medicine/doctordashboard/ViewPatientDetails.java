package com.tele.medicine.doctordashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tele.medicine.R;

/**
 * Created by Database on 8/1/2016.
 */
public class ViewPatientDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView patientNameTv, patientGenderTv, patientDiseaseTv, patientAgeTv, patientMobNumberTv, patientAddressTv, appointmentDateTimeTv;
    private String patientName, patientAge, appointmentDate, patientAddress;
    private Button ePrescriptionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_patient_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            patientName = bundle.getString("PATIENT_NAME");
            patientAge = bundle.getString("AGE");
            appointmentDate = bundle.getString("APPOINTMENT_DATE_TIME");
            patientAddress = bundle.getString("ADDRESS");
        }
        toolbar.setTitle(patientName);
        setSupportActionBar(toolbar);
        setaupIds();
        ePrescriptionButton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setaupIds()
    {
        patientNameTv = (TextView)findViewById(R.id.patientNameTv);
        patientGenderTv = (TextView)findViewById(R.id.patientGenderTv);
        patientDiseaseTv = (TextView)findViewById(R.id.patientDiseaseTv);
        patientAgeTv = (TextView)findViewById(R.id.patientAgeTv);
        patientMobNumberTv = (TextView)findViewById(R.id.patientMobNumberTv);
        patientAddressTv = (TextView)findViewById(R.id.patientAddressTv);
        appointmentDateTimeTv = (TextView)findViewById(R.id.appointmentDateTimeTv);
        ePrescriptionButton = (Button)findViewById(R.id.ePrescriptionBtn);

        patientNameTv.setText(patientName);
        patientAgeTv.setText(patientAge);
        appointmentDateTimeTv.setText(appointmentDate);
        patientAddressTv.setText(patientAddress);

    }

    @Override
    public void onClick(View v)
    {
        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        Intent intent = new Intent(ViewPatientDetails.this, E_PrescriptionForm.class);
        intent.putExtra("PATIENT_NAME", patientName);
        intent.putExtra("AGE", patientAge);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
