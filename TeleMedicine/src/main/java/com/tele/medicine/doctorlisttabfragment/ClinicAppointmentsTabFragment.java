package com.tele.medicine.doctorlisttabfragment;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.AppointmentBookTimeSlot;
import com.tele.medicine.AppointmentBookTimeSlotNew;
import com.tele.medicine.DoctorClinicInformation;
import com.tele.medicine.LocationSearch;
import com.tele.medicine.R;
import com.tele.medicine.adapterview.ConsultOnlineAdapter;
import com.tele.medicine.adapterview.ConsultOnlineAdapter.OnClickListener;
import com.tele.medicine.adapterview.SearchDoctorSpecialityAdapter;
import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;
import com.tele.medicine.utilities.GetLocationService;
import com.tele.medicine.utilities.ProjectConstant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Database on 7/4/2016.
 */
public class ClinicAppointmentsTabFragment extends Fragment implements LoadResponseViaGetService.AsyncTaskCompleateListener, OnClickListener, View.OnClickListener, SearchDoctorSpecialityAdapter.OnSearchClickListener
{

    ArrayList<HashMap<String, String>> doctorListValue;
    private RecyclerView mRecyclerView;
    private GetLocationService appLocationService;
    private ConsultOnlineAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    double mLatitude = 0;
    double mLongitude = 0;
    private String mLocationName, specialityName;
    private TextView currentLocationTv;
    boolean isLocationSetting=false;
    String url="", city="";
    ArrayList<String> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clinic_appointment_tab_fragment, container, false);

        Bundle bundle = this.getArguments();
        if(bundle!=null)
        {
            specialityName = bundle.getString("SPECIALITY_NAME");
            Log.e("SpecilityNm ", "Name " + specialityName);
        }

        setUpIds(view);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.appointmentsRV);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        currentLocationTv.setOnClickListener(this);
        return view;
    }

    private void setUpIds(View view)
    {
        currentLocationTv = (TextView)view.findViewById(R.id.currentLocationTv);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getlocationOnOffStaus();

    }

    private void getlocationOnOffStaus()
    {
        ContentResolver contentResolver = getActivity().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
        if(gpsStatus)
        {
            getCurrentLocation();
           /* startActivity(new Intent(SearchFilterScreen.this, NearBySearch.class));
            overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);*/

        }else{
            showSettingsAlert();
        }
    }

    private void showSettingsAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("GPS Settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                isLocationSetting = true;
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }

    private void getCurrentLocation()
    {
        // CURRENT LATTITUDE AND LONGITUDE FROM GPS & NTWRK
        appLocationService = new GetLocationService(getActivity());
        Location gpslocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
        if (gpslocation != null)
        {
            mLatitude = gpslocation.getLatitude();
            mLongitude = gpslocation.getLongitude();
            getAddressOfLocation(mLatitude, mLongitude);
        }
        else
        {
            Location nwLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
            if (nwLocation != null)
            {
                mLatitude = nwLocation.getLatitude();
                mLongitude = nwLocation.getLongitude();
               /* gps_latitude=netwrk_latitude;
                gps_longitude=netwrk_longitude;*/
                getAddressOfLocation(mLatitude, mLongitude);
            }
        }
    }

    private void getAddressOfLocation(double mLatitude, double mLongitude) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses  = null;
        try {
            addresses = geocoder.getFromLocation(mLatitude, mLongitude, 1);
            if(addresses!=null)
            {
                city = addresses.get(0).getLocality();
                if (city!=null)
                {
                    currentLocationTv.setText(city);
                    String baseURL= getResources().getString(R.string.search_doctor_url);
                    url = baseURL+specialityName+"/"+city+"/"+0;
                    url = url.replaceAll(" ", "%20");
                    Log.e("FindDoctrs URL.. ", "URL Formed " + url);
                    LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(getActivity(), this);
                    loadAsyncTask.execute(url);
                }
                /*String state = addresses.get(0).getAdminArea();
                String zip = addresses.get(0).getPostalCode();
                String country = addresses.get(0).getCountryName();*/
            }else
            {
                Toast.makeText(getActivity(), "Please check your intenet connection " , Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Please check your intenet connection " , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(int position, String doctorName, String clinicName, String clinicLocation)  // this click take 3 info from this class to timeslot
    {
        Intent timeSlotIntent = new Intent(getActivity(), AppointmentBookTimeSlotNew.class);   // change from AppointmentBookTimeSlot to AppointmentBookTimeSlotNew
        timeSlotIntent.putExtra("DOCTOR_NAME", doctorName);
        timeSlotIntent.putExtra("CLINIC_NAME", clinicName);
        timeSlotIntent.putExtra("CLINIC_LOCATION", clinicLocation);
        startActivity(timeSlotIntent);
       // Toast.makeText(getActivity(), "Appointment Book Button" +position , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCardClick(String doctorName, String clinicName,String clinicAddress,String timeSlot,String clinicConsultationFee) // get data from adatpter interface
    {
        Intent intent = new Intent(getActivity(), DoctorClinicInformation.class);
        intent.putExtra("DOCTOR_NAME", doctorName);
        intent.putExtra("LATITUDE", mLatitude);                   // this data move in DoctorClinicInformation class in Bundle
        intent.putExtra("LONGITUDE", mLatitude);
        intent.putExtra("CLINIC_NAME",clinicName);
        intent.putExtra("CLINICADDRESS",clinicAddress);
        intent.putExtra("TIMESLOT",timeSlot);
        intent.putExtra("CLINICCONSULTATIONFEE",clinicConsultationFee);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        Intent locationIntent = new Intent(getActivity(), LocationSearch.class);
        locationIntent.putExtra("CURRENT_LOCATION", currentLocationTv.getText().toString());  // provide key for current location
        locationIntent.putExtra("LOCATION_LIST",arrayList);

        startActivity(locationIntent);
    }

    @Override
    public void onSearchClick(String locationName) {
        Log.e("Selected City ","selected city "+locationName);
        mLocationName = locationName;

//        currentLocationTv.setText(mLocationName);


    }



     @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if(isLocationSetting )
        {
            String baseURL= getResources().getString(R.string.search_doctor_url);
            url = baseURL+specialityName+"/"+city;
            url = url.replaceAll(" ", "%20");
            Log.e("FindDoctrs URL.. ", "Resumed Form " + url);
            LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(getActivity(), this);
            loadAsyncTask.execute(url);
            isLocationSetting=false;
           getlocationOnOffStaus();
        }

         if(ProjectConstant.isLocationSelected)
         {
             currentLocationTv.setText(ProjectConstant.selectedLocation);
             ProjectConstant.isLocationSelected=false;

             String baseURL= getResources().getString(R.string.search_doctor_url);
             url = baseURL+specialityName+"/"+ProjectConstant.selectedLocation;
             url = url.replaceAll(" ", "%20");
             Log.e("FindDoctrs URL.. ", "Resumed Form " + url);
             LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(getActivity(), this);
             loadAsyncTask.execute(url);
             isLocationSetting=false;
//             getlocationOnOffStaus();

         }

    }

    @Override
    public void onTaskCompleted(String loadedResponse) {
        Log.e("DoctorList Result ", "Response     " + loadedResponse);
        doctorListValue = new ArrayList<HashMap<String, String>>();
        arrayList = new ArrayList<String>();
        arrayList.clear();
        if (loadedResponse != null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught")) {
            try {
                JSONObject jsonObject = new JSONObject(loadedResponse);
                JSONArray jsonArray = jsonObject.getJSONArray("doc");
                JSONArray jsonArray2 = jsonObject.getJSONArray("location");
                Log.e("DrFromJson","DrFromJson... "+jsonArray);
                Log.e("LocFromJson","LocFromJson... "+jsonArray2);
                for (int i = 0; i < jsonArray.length(); i++) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("NAME", jsonArray.getJSONObject(i).getString("name"));
                    hashMap.put("QUALIFICATION", jsonArray.getJSONObject(i).getString("qualification"));
                    hashMap.put("HOSPITAL_NAME", jsonArray.getJSONObject(i).getString("hospitalname"));
                    hashMap.put("ADDRESS", jsonArray.getJSONObject(i).getString("city"));
                    hashMap.put("CONSULTATION_FEE", jsonArray.getJSONObject(i).getString("fee"));
                    hashMap.put("APPOINTMENT_SLOT_TIME", jsonArray.getJSONObject(i).getString("atime"));
                    doctorListValue.add(hashMap);
                }
                for (int j =0; j<jsonArray2.length(); j++){
                    /*HashMap<String, String> hashMap2 = new HashMap<>();
                    Log.e("loc","loc obj... "+jsonArray2.getJSONObject(j).toString());
                    hashMap2.put("CITY",jsonArray2.getJSONObject(j).getString("city"));
                    arrayList.add(hashMap2);*/


                    Log.e("loc","loc obj... "+jsonArray2.getJSONObject(j).get("city"));
                    arrayList.add(jsonArray2.getJSONObject(j).get("city").toString());
                }
                Log.e("AppintmentData", "appintmentdata" + doctorListValue);
                Log.e("AppLocData","AppLocData... "+arrayList);
                mAdapter = new ConsultOnlineAdapter(getActivity(), this, "CLINIC_APPOINTMENTS", doctorListValue);
                mRecyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(getActivity())) {
                Toast.makeText(getActivity(), "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }
        }
       /* mAdapter = new ConsultOnlineAdapter(getActivity(), this,  "CLINIC_APPOINTMENTS");
        mRecyclerView.setAdapter(mAdapter);*/
    }
}
