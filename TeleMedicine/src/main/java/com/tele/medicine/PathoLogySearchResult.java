package com.tele.medicine;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.pathologyadapterview.SuggestAndPathologySearchResultAdapter;
import com.tele.medicine.utilities.GetLocationService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ASHA on 8/29/2016.
 */
public class PathoLogySearchResult extends AppCompatActivity implements SuggestAndPathologySearchResultAdapter.OnClickListener {

    private TextView currentLocationTV;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager layoutManager;
    SuggestAndPathologySearchResultAdapter mAdapter;
    private GetLocationService appLocationService;
    double mLatitude = 0;
    double mLongitude = 0;
    boolean isLocationSetting=false;
    String city="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_search_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pathology Result");
        setSupportActionBar(toolbar);

        setUpIds();
        layoutManager = new LinearLayoutManager(PathoLogySearchResult.this);
        mRecyclerview.setLayoutManager(layoutManager);

        mAdapter = new SuggestAndPathologySearchResultAdapter(PathoLogySearchResult.this);
        mRecyclerview.setAdapter(mAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getlocationOnOffStatus();
        onClick();
    }

    private void getlocationOnOffStatus() {
        ContentResolver contentResolver = this.getContentResolver();
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



    private void getCurrentLocation() {
        // CURRENT LATTITUDE AND LONGITUDE FROM GPS & NTWRK
        appLocationService = new GetLocationService(this);
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

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses  = null;
        try {
            addresses = geocoder.getFromLocation(mLatitude, mLongitude, 1);
            if(addresses!=null)
            {
                city = addresses.get(0).getLocality();
                if (city!=null)
                {
                    currentLocationTV.setText(city);
                   /* String baseURL= getResources().getString(R.string.search_doctor_url);
                    url = baseURL+"/"+city;
                    url = url.replaceAll(" ", "%20");
                    Log.e("FindDoctrs URL.. ", "URL Formed " + url);

                    LoadResponseViaGetService.AsyncTaskCompleateListener listener= (LoadResponseViaGetService.AsyncTaskCompleateListener) this;

                    LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(this, listener);
                    loadAsyncTask.execute(url);*/
                }
                /*String state = addresses.get(0).getAdminArea();
                String zip = addresses.get(0).getPostalCode();
                String country = addresses.get(0).getCountryName();*/
            }else
            {
                Toast.makeText(this, "Please check your intenet connection ", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Please check your intenet connection " , Toast.LENGTH_SHORT).show();
        }
    }

    private void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
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

    private void onClick() {

    }

    private void setUpIds() {
        currentLocationTV = (TextView)findViewById(R.id.currentLocationTv);
        mRecyclerview = (RecyclerView)findViewById(R.id.patholagyRV);
    }


    @Override
    public void onCardClick(int position, String pathologyName, String address) {

        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        Intent intent = new Intent(PathoLogySearchResult.this, PathologyTestViewDetails.class);
        intent.putExtra("PATHOLOGY_NAME", pathologyName);
        intent.putExtra("PATHOLOGY_LOCATION", address);
        intent.putExtra("FROM_PATHOLOGY","allpathology");
        startActivity(intent);
    }
}
