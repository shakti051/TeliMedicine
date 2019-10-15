package com.tele.medicine.doctorlisttabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tele.medicine.AppoinmentConfirmation;
import com.tele.medicine.R;
import com.tele.medicine.adapterview.TimeSlotRecyclerViewAdapter;

/**
 * Created by Database on 7/8/2016.
 */
public class TimeSlotSlideFragment extends Fragment implements TimeSlotRecyclerViewAdapter.OnTimeSlotClickListener
{
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    TimeSlotRecyclerViewAdapter adapter;
    String doctorName, clinicname, appointmentDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_slot_pager_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_View);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new TimeSlotRecyclerViewAdapter(getActivity(), this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle b = getArguments();
        if (b!=null) {
            doctorName = b.getString("DR_NAME");
            clinicname = b.getString("CLINIC_NAME");
            appointmentDate = b.getString("APPOINTMENT_DATE");
        }

    }

    @Override
    public void onClick(String timeValue)
    {
        Intent intent = new Intent(getActivity(), AppoinmentConfirmation.class);
        intent.putExtra("DR_NAME", doctorName);
        intent.putExtra("CLINIC_NAME", clinicname);
        intent.putExtra("APPOINTMENT_DATE", appointmentDate);
        intent.putExtra("APPOINTMENT_TIME", timeValue);
        startActivity(intent);

    }
}
