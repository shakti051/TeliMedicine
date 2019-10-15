package com.tele.medicine.doctorlisttabfragment;

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

import com.tele.medicine.AppointmentBookTimeSlot;
import com.tele.medicine.ConsultOnlineDoctor;
import com.tele.medicine.R;
import com.tele.medicine.adapterview.ConsultOnlineAdapter;
import com.tele.medicine.adapterview.SearchDoctorSpecialityAdapter;

/**
 * Created by Database on 7/4/2016.
 */
public class ConsultOnlineTabFragment extends Fragment implements ConsultOnlineAdapter.OnClickListener
{
    private RecyclerView mRecyclerView;
    private ConsultOnlineAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.consultonline_tab_fragment, container, false);
        setUpIds(view);

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void setUpIds(View view)
    {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new ConsultOnlineAdapter(getActivity(), this, "CONSULT_ONLINE");
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(int position, String doctorName, String clinicName, String clinicLocation)
    {

        // Toast.makeText(getActivity(), "Consult Button" + position, Toast.LENGTH_SHORT).show();
        Intent timeSlotIntent = new Intent(getActivity(), ConsultOnlineDoctor.class);
        timeSlotIntent.putExtra("DOCTOR_NAME", doctorName);
        timeSlotIntent.putExtra("CLINIC_NAME", clinicName);
        timeSlotIntent.putExtra("CLINIC_LOCATION", clinicLocation);
        startActivity(timeSlotIntent);
    }

    @Override
    public void onCardClick(String doctorName, String clinicName, String clinicAddress, String timeSlot,String clinicConsultationFee)
    {
       /* Intent timeSlotIntent = new Intent(getActivity(), ConsultOnlineDoctor.class);
        startActivity(timeSlotIntent);*/
    }
}
