package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.adapterview.AppointmentTimeSlotAdapter;
import com.tele.medicine.doctorlisttabfragment.TimeSlotSlideFragment;
import com.tele.medicine.utilities.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Database on 7/8/2016.
 */
public class AppointmentBookTimeSlot extends AppCompatActivity implements AppointmentTimeSlotAdapter.OnTimeSlotClickListener,View.OnClickListener {
    private TextView doctorNameTv, clinicNameTv, setDateTv;
    private CircularImageView doctorIV;
    private ImageView forwordBtnIV, previousBtnIV;
    private String doctorName, clinicName, cliniclocation;
    private ViewPager mPager;
    private AppointmentTimeSlotAdapter mAdapter;
    private int NUM_PAGES = 6;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    String selectedTimeSlot="";
    private LinearLayout bookAppointmentDoctorLayout;

    Calendar c;
    SimpleDateFormat df;
    String formattedDate;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_book_timeslot);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Time Slot");
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AppointmentBookTimeSlot.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            doctorName = bundle.getString("DOCTOR_NAME");
            clinicName = bundle.getString("CLINIC_NAME");
            cliniclocation = bundle.getString("CLINIC_LOCATION");
        }
        setUpIds();
        forwordBtnIV.setOnClickListener(this);
        previousBtnIV.setOnClickListener(this);
        bookAppointmentDoctorLayout.setOnClickListener(this);
       /* mPagerAdapter = new AppointmentTimeSlotPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);*/
       /* mAdapter = new AppointmentTimeSlotAdapter(AppointmentBookTimeSlot.this);
        mRecyclerView.setAdapter(mAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
    }


    private void setUpIds()
    {
        bookAppointmentDoctorLayout = (LinearLayout)findViewById(R.id.bookAppointmentDoctorDetails);
        doctorNameTv = (TextView)findViewById(R.id.doctorNameTv);
        clinicNameTv = (TextView)findViewById(R.id.clinicNameTv);
        doctorIV = (CircularImageView)findViewById(R.id.circularImgViewTv);
        setDateTv = (TextView)findViewById(R.id.setDateTv);
        forwordBtnIV = (ImageView)findViewById(R.id.forwardBtnIv);
        previousBtnIV = (ImageView)findViewById(R.id.backBtnIv);
       // mPager = (ViewPager) findViewById(R.id.pager);

        c = Calendar.getInstance();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        setDateTv.setText("Today, " + formattedDate);
        doctorNameTv.setText(doctorName);
        clinicNameTv.setText(clinicName + ", " + cliniclocation);
       // doctorNameTv.setText(doctorName);
    }
    /*@Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }*/

    @Override
    public void onClick(View v)
    {
            if (v.getId()==R.id.forwardBtnIv)
            {
                counter++;
                if (counter<6)
                {
                    if (counter==1)
                    {
                       // mPager.setCurrentItem(counter,true);
                        previousBtnIV.setVisibility(View.VISIBLE);
                        c.add(Calendar.DATE, 1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText("Tommorow, "+formattedDate);
                    }
                    if (counter==2)
                    {
                      //  mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, 1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);
                    }
                    if (counter==3)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, 1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);
                    }
                    if (counter==4)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, 1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);
                    }
                    if (counter==5)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, 1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);
                        forwordBtnIV.setVisibility(View.INVISIBLE);
                    }
                }
            }
            if (v.getId()==R.id.backBtnIv)
            {
                counter--;
                if (counter>=0)
                {
                    if (counter==0)
                    {
                      //  mPager.setCurrentItem(counter,true);
                        previousBtnIV.setVisibility(View.INVISIBLE);
                        forwordBtnIV.setVisibility(View.VISIBLE);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText("Today, "+formattedDate);
                    }
                    if (counter==1)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText("Tommorow, "+formattedDate);
                    }
                    if (counter==2)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);

                    }
                    if (counter==3)
                    {
                       // mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);
                    }
                    if (counter==4)
                    {
                      //  mPager.setCurrentItem(counter,true);
                        forwordBtnIV.setVisibility(View.VISIBLE);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);

                    }
                    if (counter==5)
                    {
                      //  mPager.setCurrentItem(counter,true);
                        c.add(Calendar.DATE, -1);
                        formattedDate = df.format(c.getTime());
                        setDateTv.setText(formattedDate);

                    }
                }
            }
        if (v.getId()==R.id.bookAppointmentDoctorDetails)
        {
            Intent intent = new Intent(AppointmentBookTimeSlot.this, DoctorClinicInformation.class);
            intent.putExtra("DOCTOR_NAME", doctorName);
            /*intent.putExtra("LATITUDE", mLatitude);
            intent.putExtra("LONGITUDE", mLatitude);*/
            startActivity(intent);
        }
    }

    /*public class AppointmentTimeSlotPagerAdapter extends FragmentStatePagerAdapter
    {
        public AppointmentTimeSlotPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Bundle bundle = new Bundle();
            bundle.putString("DR_NAME", doctorName);
            bundle.putString("CLINIC_NAME", clinicNameTv.getText().toString());
            bundle.putString("APPOINTMENT_DATE", setDateTv.getText().toString());
            TimeSlotSlideFragment frag = new TimeSlotSlideFragment();
            frag.setArguments(bundle);
            return frag;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
*/

    @Override
    public void onClick(String time)
    {
       selectedTimeSlot = time;
    }

    public void bookAppointmentButton(View view)
    {
        Intent intent = new Intent(AppointmentBookTimeSlot.this, AppoinmentConfirmation.class);
        intent.putExtra("DR_NAME", doctorName);
        intent.putExtra("CLINIC_NAME", clinicName);
        intent.putExtra("APPOINTMENT_DATE", setDateTv.getText().toString());
        intent.putExtra("APPOINTMENT_TIME", selectedTimeSlot);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
