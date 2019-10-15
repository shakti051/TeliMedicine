package com.tele.medicine.doctordashboard.doctorhometabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tele.medicine.R;
import com.tele.medicine.doctoradapterview.DoctorAppointmentFixedAdapter;
import com.tele.medicine.doctordashboard.BookedPatientConfirmation;
import com.tele.medicine.doctordashboard.ViewPatientDetails;

/**
 * Created by Database on 7/14/2016.
 */
public class DoctorHomeTabFragment extends Fragment implements DoctorAppointmentFixedAdapter.OnClickListener
{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DoctorAppointmentFixedAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_home_tab_fragment, container, false);

        setUpIds(view);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.appointmentsRV);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DoctorAppointmentFixedAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpIds(View view) {

    }

    @Override
    public void onCardClick(String patientName, String age, String dateTime, String city)
    {
        Intent intent = new Intent(getActivity(), ViewPatientDetails.class);
        intent.putExtra("PATIENT_NAME", patientName);
        intent.putExtra("AGE", age);
        intent.putExtra("APPOINTMENT_DATE_TIME", dateTime);
        intent.putExtra("ADDRESS", city);
        startActivity(intent);
       //  Toast.makeText(getActivity(), "Patient Name = " +patientName , Toast.LENGTH_SHORT).show();
    }
}
