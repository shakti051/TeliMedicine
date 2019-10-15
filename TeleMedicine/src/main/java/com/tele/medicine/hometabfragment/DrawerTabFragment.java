package com.tele.medicine.hometabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tele.medicine.R;
import com.tele.medicine.utilities.SessionManager;

/**
 * Created by Database on 6/29/2016.
 */
public class DrawerTabFragment extends Fragment implements View.OnClickListener {
    private LinearLayout signOutLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_settings_fragment, container, false);
        setUpIds(view);
        signOutLayout.setOnClickListener(this);
        return view;
    }

    private void setUpIds(View view)
    {
        signOutLayout = (LinearLayout)view.findViewById(R.id.signOutLayout);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onClick(View v) {     // for logout from patitent
        SessionManager  session = new SessionManager(getActivity());
        session.logoutUser();
    }
}
