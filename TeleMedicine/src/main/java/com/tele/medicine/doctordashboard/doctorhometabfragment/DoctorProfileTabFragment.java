package com.tele.medicine.doctordashboard.doctorhometabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tele.medicine.R;
import com.tele.medicine.UserProfileDetails;
import com.tele.medicine.doctordashboard.DoctorCalenderManagement;
import com.tele.medicine.doctordashboard.DoctorOpdManagement;
import com.tele.medicine.doctordashboard.DoctorProfileDetails;
import com.tele.medicine.doctordashboard.FixedAppointment;

/**
 * Created by Database on 7/14/2016.
 */
public class DoctorProfileTabFragment extends Fragment implements View.OnClickListener {
    private LinearLayout viewProfileLayout, fixedAppointmentLayout, calenderMgmtLayout, opdMgmtLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_profile_tab_fragment, container, false);

        setUpIds(view);
        viewProfileLayout.setOnClickListener(this);
        fixedAppointmentLayout.setOnClickListener(this);
        calenderMgmtLayout.setOnClickListener(this);
        opdMgmtLayout.setOnClickListener(this);
        return view;
    }

    private void setUpIds(View view)
    {
        viewProfileLayout = (LinearLayout)view.findViewById(R.id.doctorProfileLayout);
        fixedAppointmentLayout = (LinearLayout)view.findViewById(R.id.fixedAppointmentLayout);
        calenderMgmtLayout = (LinearLayout)view.findViewById(R.id.calenderMgmtLayout);
        opdMgmtLayout = (LinearLayout)view.findViewById(R.id.opdManagmentLayout);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.doctorProfileLayout:
                Intent profileIntent = new Intent(getActivity(), DoctorProfileDetails.class);
                startActivity(profileIntent);
                break;
            case R.id.fixedAppointmentLayout:
                Intent fixedAppointmentIntent = new Intent(getActivity(), FixedAppointment.class);
                startActivity(fixedAppointmentIntent);
                break;
            case R.id.calenderMgmtLayout:
                Intent calMgmtIntent = new Intent(getActivity(), DoctorCalenderManagement.class);
                startActivity(calMgmtIntent);
                break;
            case R.id.opdManagmentLayout:
                Intent opdMgmtIntent = new Intent(getActivity(), DoctorOpdManagement.class);
                startActivity(opdMgmtIntent);
                break;
        }
    }
}
