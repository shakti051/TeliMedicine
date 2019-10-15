package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tele.medicine.adapterview.BookedAppointmentListAdapter;
import com.tele.medicine.doctoradapterview.DoctorAppointmentFixedAdapter;

import java.util.ArrayList;

/**
 * Created by Database on 7/20/2016.
 */
public class AppointmentBookedViewDetails extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BookedAppointmentListAdapter mAdapter;
    private Button bookAppointmentProceedBtn;
    private Bundle bundle;
    String doctorName, clinicName, appointmentDate, appointmentTime;
    private LinearLayout bookedAppointmentLayout, noAppointmentYetLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_booked_view_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Appointments");
        setSupportActionBar(toolbar);

        bundle = getIntent().getExtras();  // this bundel get data from AppoinmentConfirmation class through intent
        Log.e("BundelResponse ","response.."+bundle);
        if (bundle!=null)
        {
            doctorName = bundle.getString("DR_NAME");
            clinicName = bundle.getString("CLINIC_NAME");
            appointmentDate = bundle.getString("APPOINTMENT_DATE");
            appointmentTime = bundle.getString("APPOINTMENT_TIME");
        }
        mRecyclerView = (RecyclerView)findViewById(R.id.appointmentsRV);
        layoutManager = new LinearLayoutManager(AppointmentBookedViewDetails.this);
        mRecyclerView.setLayoutManager(layoutManager);

        setUpIds();
        checkAppointment();
        bookAppointmentProceedBtn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void checkAppointment()
    {
        if (bundle!=null)
        {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(doctorName);
            arrayList.add(clinicName);
            arrayList.add(appointmentDate);
            arrayList.add(appointmentTime);

            Log.e("Listtt ", "Valueeee" + arrayList);

            noAppointmentYetLayout.setVisibility(View.GONE);
            bookedAppointmentLayout.setVisibility(View.VISIBLE);
            mAdapter = new BookedAppointmentListAdapter(AppointmentBookedViewDetails.this, doctorName, clinicName, appointmentDate, appointmentTime);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

    private void setUpIds()
    {
        bookAppointmentProceedBtn = (Button)findViewById(R.id.bookAppointmentBtn);
        bookedAppointmentLayout = (LinearLayout)findViewById(R.id.bookedAppointmentLayout);
        noAppointmentYetLayout = (LinearLayout)findViewById(R.id.noAppointmentLayout);
    }


    @Override
    public void onClick(View v)
    {
        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        startActivity(new Intent(AppointmentBookedViewDetails.this, SearchResultActivity.class));
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
