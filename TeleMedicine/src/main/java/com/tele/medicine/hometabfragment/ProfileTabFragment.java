package com.tele.medicine.hometabfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.AppointmentBookedViewDetails;
import com.tele.medicine.R;
import com.tele.medicine.SearchResultActivity;
import com.tele.medicine.UserProfileDetails;
import com.tele.medicine.UserProfileDetailsNew;
import com.tele.medicine.ViewUserDetails;

/**
 * Created by Database on 6/29/2016.
 */
public class ProfileTabFragment extends Fragment implements View.OnClickListener {
    private LinearLayout viewProfileLayout, mConsultLayout, mAppointmentLayout, mPrescriptionLayout,mDocumentsLayout;
    private TextView userNameTv;
    private SharedPreferences preference;
    private ImageView updateProfile,viewProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_tab_fragment, container, false);
        setUpIds(view);

//        viewProfileLayout.setOnClickListener(this);
        mConsultLayout.setOnClickListener(this);
        mAppointmentLayout.setOnClickListener(this);
        viewProfile.setOnClickListener(this);
        updateProfile.setOnClickListener(this);
        return view;
    }

    private void setUpIds(View view)
    {
       /* preference = this.getActivity().getSharedPreferences("TM_Preference", Context.MODE_PRIVATE);
        Log.e("Loggedddddd ", "profile "+preference.getString("USER_EMAIL_ID", "nodata"));*/

        userNameTv = (TextView)view.findViewById(R.id.userNameTv);
        viewProfileLayout = (LinearLayout)view.findViewById(R.id.userAccountLayout);
        mConsultLayout = (LinearLayout)view.findViewById(R.id.accountConsultLayout);
        mAppointmentLayout = (LinearLayout)view.findViewById(R.id.appointmentLayout);
        mPrescriptionLayout = (LinearLayout)view.findViewById(R.id.accountPrescriptionLayout);
        mDocumentsLayout = (LinearLayout)view.findViewById(R.id.accountDocumentLayout);

        updateProfile = (ImageView)view.findViewById(R.id.updateProfileIV);
        viewProfile = (ImageView)view.findViewById(R.id.viewProfileIV);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*String userEmailId = preference.getString("USER_EMAIL_ID", "nodata");
        userNameTv.setText(userEmailId);*/
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.viewProfileIV:
                Intent viewProfileIntent = new Intent(getActivity(), ViewUserDetails.class);
//                viewProfileIntent.putExtra("FROMVIEWPROFILE","fromviewprofile");
                startActivity(viewProfileIntent);
                break;
            case R.id.accountConsultLayout:
              /*  Intent consultIntent = new Intent(getActivity(), UserProfileDetails.class);
                startActivity(consultIntent);*/
                break;
            case R.id.appointmentLayout:
                Intent appointmentIntent = new Intent(getActivity(), AppointmentBookedViewDetails.class);
                startActivity(appointmentIntent);
                break;
            case R.id.updateProfileIV:
//                Toast.makeText(getActivity(),"Edit Profile",Toast.LENGTH_LONG).show();
                Intent updateProfileIntent = new Intent(getActivity(), UserProfileDetailsNew.class);
//                updateProfileIntent.putExtra("FROMVIEWPROFILE","fromupdateprofile");
                startActivity(updateProfileIntent);
                break;
        }

    }
}
