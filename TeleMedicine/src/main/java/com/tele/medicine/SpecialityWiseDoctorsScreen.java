package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tele.medicine.adapterview.DoctorListViewPagerAdapter;
import com.tele.medicine.doctorlisttabfragment.ClinicAppointmentsTabFragment;
import com.tele.medicine.doctorlisttabfragment.ConsultOnlineTabFragment;

/**
 * Created by Database on 7/4/2016.
 */
public class SpecialityWiseDoctorsScreen extends AppCompatActivity
{
    private ImageView searchIconIv;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String specialityName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialities_wise_doctors);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        searchIconIv = (ImageView)findViewById(R.id.toolbar_searchIcon);
        searchIconIv.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            if (bundle.containsKey("SPECIALITY_NAME")) {
                specialityName = bundle.getString("SPECIALITY_NAME");
                toolbar.setTitle(specialityName);
                setSupportActionBar(toolbar);
            } else if (bundle.containsKey("VIEW_NAME")) {
                specialityName = bundle.getString("VIEW_NAME");
                toolbar.setTitle(specialityName);
                setSupportActionBar(toolbar);
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        searchIconIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager)
    {
        DoctorListViewPagerAdapter mAdapter = new DoctorListViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new ConsultOnlineTabFragment(), "Consult OnLine");

        Bundle bundle = new Bundle();
        bundle.putString("SPECIALITY_NAME", specialityName);

        ClinicAppointmentsTabFragment clinicAppointmentTabFrag = new ClinicAppointmentsTabFragment();
        clinicAppointmentTabFrag.setArguments(bundle);

        mAdapter.addFragment(clinicAppointmentTabFrag, "Appointments");
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
