package com.tele.medicine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.adapterview.AppointmentTimeSlotAdapter;
import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.utilities.CircularImageView;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by ASHA on 8/20/2016.
 */
public class AppointmentBookTimeSlotNew extends AppCompatActivity implements AppointmentTimeSlotAdapter.OnTimeSlotClickListener, View.OnClickListener,LoadResponseViaGetService.AsyncTaskCompleateListener {

    private TextView doctorNameTv, clinicNameTv, setDateTv,appointmentDateTv;
    private ImageView forwordBtnIV, previousBtnIV;
    private String doctorName, clinicName, cliniclocation;
    private AppointmentTimeSlotAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    String selectedTimeSlot = "";
    private LinearLayout bookAppointmentDoctorLayout;
    private CircularImageView doctorIV;
    static final int DATE_DIALOG_ID = 0;
    int dateFlag = 0;
    private int pYear, pMonth, pDay;
    RelativeLayout setAppointmentDateLayout;
    String url="",doctorID="a",appointmentDate="b";
    ArrayList<String> listValue;


    Calendar cal;
    SimpleDateFormat df;
    String formattedDate;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_book_timeslot_new);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Time Slot");
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AppointmentBookTimeSlotNew.this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            doctorName = bundle.getString("DOCTOR_NAME");
            clinicName = bundle.getString("CLINIC_NAME");
            cliniclocation = bundle.getString("CLINIC_LOCATION");
        }
        setUpIds();
        setAppointmentDateLayout.setOnClickListener(this);
        bookAppointmentDoctorLayout.setOnClickListener(this);
        /*mAdapter = new AppointmentTimeSlotAdapter(AppointmentBookTimeSlotNew.this);
        mRecyclerView.setAdapter(mAdapter);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createTimeSlotList();

    }

    private void createTimeSlotList() {

        String baseURL = "http://49.50.64.226:9000/ords/consultit/doctordatetime/";
        url = baseURL+doctorID+"/"+appointmentDate;
        url = url.replaceAll(" ", "%20");
        Log.e("TimeSlot URLLL.. ", "URL Formed " + url);

        LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(AppointmentBookTimeSlotNew.this);
        loadAsyncTask.execute(url);
    }

    private void setUpIds() {
        bookAppointmentDoctorLayout = (LinearLayout) findViewById(R.id.bookAppointmentDoctorDetails);
        doctorNameTv = (TextView) findViewById(R.id.doctorNameTv);
        clinicNameTv = (TextView) findViewById(R.id.clinicNameTv);
        doctorIV = (CircularImageView) findViewById(R.id.circularImgViewTv);
        setAppointmentDateLayout = (RelativeLayout) findViewById(R.id.appointmentDateID);
        appointmentDateTv = (TextView)findViewById(R.id.appointmentDateTvID);
        setDateTv = (TextView)findViewById(R.id.appointmentDateTvID);

        cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(cal.getTime());

//        setDateTv.setText("Today, " + formattedDate);
        doctorNameTv.setText(doctorName);
        clinicNameTv.setText(clinicName + ", " + cliniclocation);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bookAppointmentDoctorDetails:
                Intent intent = new Intent(AppointmentBookTimeSlotNew.this, DoctorClinicInformation.class);
                intent.putExtra("DOCTOR_NAME", doctorName);
            /*intent.putExtra("LATITUDE", mLatitude);
            intent.putExtra("LONGITUDE", mLatitude);*/
                startActivity(intent);
                break;
            case R.id.appointmentDateID:
                dateFlag = 1;
                showDialog(DATE_DIALOG_ID);
                break;
        /*if (v.getId() == R.id.bookAppointmentDoctorDetails) {
            Intent intent = new Intent(AppointmentBookTimeSlotNew.this, DoctorClinicInformation.class);
            intent.putExtra("DOCTOR_NAME", doctorName);
            *//*intent.putExtra("LATITUDE", mLatitude);
            intent.putExtra("LONGITUDE", mLatitude);*//*
            startActivity(intent);
        }*/

        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,pickerListener,pYear,pMonth,pDay);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            pYear  = selectedYear;
            pMonth = selectedMonth;
            pDay   = selectedDay;

            // Show selected date
            appointmentDateTv.setText(new StringBuilder().append(pMonth + 1)
                    .append("-").append(pDay).append("-").append(pYear)
                    .append(" "));

        }
    };

    @Override
    public void onClick(String time) {

        selectedTimeSlot = time;
    }

    public void bookAppointmentButton(View view) {     // this btn click take 4 data from this class to appointmentconfirmation class
        Intent intent = new Intent(AppointmentBookTimeSlotNew.this, AppoinmentConfirmation.class);
        intent.putExtra("DR_NAME", doctorName);
        intent.putExtra("CLINIC_NAME", clinicName);
        intent.putExtra("APPOINTMENT_DATE", setDateTv.getText().toString());
        intent.putExtra("APPOINTMENT_TIME", selectedTimeSlot);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onTaskCompleted(String loadedResponse) {
        Log.e("TimeSlotResponse","Result.. " +loadedResponse);
        listValue = new ArrayList<String>();
        if(loadedResponse!=null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught")){
            try{

                JSONObject jsonObj = new JSONObject(loadedResponse);
                JSONArray arrObj = jsonObj.getJSONArray("items");

                for(int i=0; i<arrObj.length(); i++){
                    listValue.add(arrObj.getJSONObject(i).getString("dtime"));
                    Log.e("KerCheck..","kercheck.."+arrObj.getJSONObject(i).getString("dtime"));
                }
                Log.e("Check list..","check list.. "+listValue);
                mAdapter = new AppointmentTimeSlotAdapter(listValue,AppointmentBookTimeSlotNew.this);
                mRecyclerView.setAdapter(mAdapter);
            }catch (Exception e){

            }
        }else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(AppointmentBookTimeSlotNew.this)) {
                Toast.makeText(this, "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }
        }

    }
}
