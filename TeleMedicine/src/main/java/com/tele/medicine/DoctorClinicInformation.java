package com.tele.medicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Database on 7/12/2016.
 */
public class DoctorClinicInformation extends AppCompatActivity
{
    GoogleMap googleMap;
    SupportMapFragment fm;
    private String doctorName,clinicName, clinicAddress, timeSlot, clinicConsultationFee;
    double mLatitude, mLongitude;

    TextView doctorNameTV, clinicNameTV, clinicConsultaionFeeTV, timeSlotTV, clinicAddressTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_clinic_info);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            doctorName = bundle.getString("DOCTOR_NAME");
            mLatitude = bundle.getDouble("LATITUDE");
            mLongitude = bundle.getDouble("LONGITUDE");
            clinicName = bundle.getString("CLINIC_NAME");
            clinicAddress = bundle.getString("CLINICADDRESS");
            timeSlot = bundle.getString("TIMESLOT");
            clinicConsultationFee = bundle.getString("CLINICCONSULTATIONFEE");

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(doctorName);
        setSupportActionBar(toolbar);

        setUpIds();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setValues();

    }

    private void setValues() {

        clinicNameTV.setText(clinicName);
        clinicAddressTV.setText(clinicAddress);
        timeSlotTV.setText(timeSlot);
        clinicConsultaionFeeTV.setText(clinicConsultationFee);
    }

    private void setUpIds()
    {
        fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.setMyLocationEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(28.6014, 77.3181))
                .zoom(14)
                .bearing(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.6014, 77.3181))
                .title("New Delhi India"));

        clinicNameTV = (TextView) findViewById(R.id.clinicNameTv);
        clinicConsultaionFeeTV = (TextView) findViewById(R.id.clinicConsultaionLabelTv);
        timeSlotTV = (TextView) findViewById(R.id.clinicTimeSlotTv);
        clinicAddressTV = (TextView) findViewById(R.id.clinicAddressTv);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
