package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.hometabfragment.HomeTabFragment;

/**
 * Created by Database on 7/11/2016.
 */
public class AppoinmentConfirmation extends AppCompatActivity implements View.OnClickListener {
    String doctorName, clinicName, appointmentDate, appointmentTime;
    private TextView doctorNameTv, clinicNameTv, confirmDateTv, confirmTimeTv;
    private Button appointmentConfirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_confirmation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Confirmation");
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();  // this bundel get data from appointmentbooktimeslot class through intent
        if (bundle!=null)
        {
            doctorName = bundle.getString("DR_NAME");
            clinicName = bundle.getString("CLINIC_NAME");
            appointmentDate = bundle.getString("APPOINTMENT_DATE");
            appointmentTime = bundle.getString("APPOINTMENT_TIME");
        }

        setUpIds();
        appointmentConfirmBtn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpIds()
    {
        doctorNameTv = (TextView)findViewById(R.id.clinicDoctorNameTv);
        clinicNameTv = (TextView)findViewById(R.id.clinicNameTv);
        confirmDateTv = (TextView)findViewById(R.id.confirmDateTv);
        confirmTimeTv = (TextView)findViewById(R.id.confirmTimeTv);
        appointmentConfirmBtn = (Button)findViewById(R.id.appointmentConfirmBtn);

        doctorNameTv.setText(doctorName);
        clinicNameTv.setText(clinicName);
        confirmDateTv.setText(appointmentDate);
        confirmTimeTv.setText(appointmentTime);
        /*doctorNameTv = (TextView)findViewById(R.id.clinicDoctorNameTv);
        doctorNameTv = (TextView)findViewById(R.id.clinicDoctorNameTv);
        doctorNameTv = (TextView)findViewById(R.id.clinicDoctorNameTv);
        doctorNameTv = (TextView)findViewById(R.id.clinicDoctorNameTv);*/
    }

    @Override
    public void onClick(View v)    // this btn send 4 data to AppointmentBookedViewDetails class
    {
        /*overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        Intent intent = new Intent(AppoinmentConfirmation.this, AppointmentBookedViewDetails.class);
        intent.putExtra("DR_NAME", doctorName);
        intent.putExtra("CLINIC_NAME", clinicName);
        intent.putExtra("APPOINTMENT_DATE", appointmentDate);
        intent.putExtra("APPOINTMENT_TIME", appointmentTime);

        startActivity(intent);*/
        Toast.makeText(this,"Your Appointment Booked",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(AppoinmentConfirmation.this,HomeScreen.class);
        startActivity(intent);
        finish();
      //  startActivity(new Intent(AppoinmentConfirmation.this, AppointmentPaymentMode.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
