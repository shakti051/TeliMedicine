package com.tele.medicine.doctordashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tele.medicine.AppointmentBookedViewDetails;
import com.tele.medicine.R;
import com.tele.medicine.doctoradapterview.DoctorAppointmentFixedAdapter;

/**
 * Created by Database on 7/15/2016.
 */
public class FixedAppointment extends AppCompatActivity implements DoctorAppointmentFixedAdapter.OnClickListener
{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DoctorAppointmentFixedAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_appointment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Fixed Appointment");
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.appointmentsRV);
        layoutManager = new LinearLayoutManager(FixedAppointment.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DoctorAppointmentFixedAdapter(FixedAppointment.this);
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onCardClick(String patientName, String age, String dateTime, String address)
    {
        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        Intent intent = new Intent(FixedAppointment.this, ViewPatientDetails.class);
        intent.putExtra("PATIENT_NAME", patientName);
        intent.putExtra("AGE", age);
        intent.putExtra("APPOINTMENT_DATE_TIME", dateTime);
        intent.putExtra("ADDRESS", address);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
