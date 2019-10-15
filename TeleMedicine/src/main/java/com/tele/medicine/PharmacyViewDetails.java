package com.tele.medicine;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tele.medicine.utilities.GetLocationService;

import java.util.Random;

/**
 * Created by Database on 7/29/2016.
 */
public class PharmacyViewDetails extends AppCompatActivity implements View.OnClickListener
{
    private TextView pharmacyNameTv, pharmacyLocationTv, pharmacyMobileTv;
    String pharmacyName, pharmacyLocation;
    private ImageView img;
    private PopupWindow popupWindow;
    private GetLocationService appLocationService;
    GoogleMap googleMap;
    SupportMapFragment fm;
    double mLatitude = 0;
    double mLongitude = 0;
    public TextView pickFromGalleryTv, pickFromCameraTv, closeTv, uploadPrescriptionTv, e_PrescriptionTv;

    final Random rnd = new Random();
    int randomNumber = rnd.nextInt(3);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_view_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            pharmacyName = bundle.getString("PHARMACY_NAME");
            pharmacyLocation = bundle.getString("PHARMACY_LOCATION");
        }
        toolbar.setTitle(pharmacyName);
        setSupportActionBar(toolbar);

        setUpIds();
        img = (ImageView) findViewById(R.id.advertisIv);
        final String str = "banner_" + randomNumber;
        int image_ID = getResources().getIdentifier(str, "drawable", getPackageName());
        img.setBackgroundResource(image_ID);

       // uploadPrescriptionlayout.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void setUpIds() {
        getCurrentLocation();
        fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = fm.getMap();
        googleMap.setMyLocationEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(28.6014, 77.3181))
                .zoom(14)
                .bearing(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.6014, 77.3181)));
        //.title("New Delhi India"));

        pharmacyNameTv = (TextView) findViewById(R.id.pharmacyNameTv);
        pharmacyLocationTv = (TextView) findViewById(R.id.addressTv);
        pharmacyMobileTv = (TextView) findViewById(R.id.mobileTv);
        uploadPrescriptionTv = (TextView) findViewById(R.id.uploadPrescriptionTv);
        e_PrescriptionTv = (TextView) findViewById(R.id.e_PrescriptionTv);

        pharmacyNameTv.setText(pharmacyName);
        pharmacyLocationTv.setText(pharmacyLocation);

        uploadPrescriptionTv.setOnClickListener(this);
        e_PrescriptionTv.setOnClickListener(this);
    }

    private void popUpAlert()
    {
        LayoutInflater inflater = (LayoutInflater)PharmacyViewDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog, (ViewGroup) findViewById(R.id.popElement));
        popupWindow = new PopupWindow(layout,layout.getLayoutParams().MATCH_PARENT, layout.getLayoutParams().MATCH_PARENT,true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);

        pickFromGalleryTv = (TextView)layout.findViewById(R.id.openGalleryTv);
        pickFromCameraTv = (TextView)layout.findViewById(R.id.openCameraTv);
        closeTv = (TextView)layout.findViewById(R.id.notNowLayout);
        closeTv.setOnClickListener(this);
    }

    private void getCurrentLocation()
    {
        appLocationService = new GetLocationService(PharmacyViewDetails.this);
        Location gpslocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
        if (gpslocation != null)
        {
            mLatitude = gpslocation.getLatitude();
            mLongitude = gpslocation.getLongitude();

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
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.uploadPrescriptionTv:
                popUpAlert();
                break;
            case R.id.e_PrescriptionTv:
                Toast.makeText(PharmacyViewDetails.this, "Uploading... ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notNowLayout:
                popupWindow.dismiss();
                break;
            case R.id.openGalleryTv:
                break;
            case R.id.openCameraTv:
                break;
            default:
                break;
        }
    }
}
