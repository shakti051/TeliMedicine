package com.tele.medicine.doctordashboard.doctorhometabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tele.medicine.R;
import com.tele.medicine.utilities.SessionManager;

/**
 * Created by Database on 7/14/2016.
 */
public class DoctorSettingsTabFragment extends Fragment implements View.OnClickListener
{
    private LinearLayout signOutLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_setting_tab_fragment, container, false);
        setUpIds(view);
        signOutLayout.setOnClickListener(this);
        return view;
    }

    private void setUpIds(View view) {
        signOutLayout = (LinearLayout)view.findViewById(R.id.signOutLayout);

    }
    @Override
    public void onClick(View v) {     // for logout from doctor
        SessionManager session = new SessionManager(getActivity());
        session.logoutUser();
    }
}
