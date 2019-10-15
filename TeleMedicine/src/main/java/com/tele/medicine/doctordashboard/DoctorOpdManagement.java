package com.tele.medicine.doctordashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tele.medicine.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Database on 8/3/2016.
 */
public class DoctorOpdManagement extends AppCompatActivity implements View.OnClickListener {
    private TextView selectStartTimeTv, setStartTimeTv, selectEndTimeTv, setEndTimeTv;
    ArrayAdapter<String> mDaysAdapter, mStatesAdapter, mCityAdapter;
    private Spinner mDaysSp, mStatesSp, mCitySp;
    static final int TIME_DIALOG_ID = 1;
    int timeFlag = 0;
    private int mHour, mMinute;
    private Button createOpdProceedButton;

    public String daysValues[] = { "Select Days", "Monday ", "Tuesday ", "Wednesday ", "Thursday ", "Friday ", "Saturday "};
    public String statesValues[] = { "Select States", "Uttar Pradesh ", "Delhi ", "Maharashtra ", "Madhya Pradesh ", "Bihar ", "Others "};
    public String cityValues[] = { "Select City", "Delhi ", "Noida ", "Varanasi ", "Indore ", "Mumbai ", "Patna ", "Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_opd_mgmt);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("OPD Management");
        setSupportActionBar(toolbar);

        setUpIds();
        mDaysAdapter = new ArrayAdapter<String>(DoctorOpdManagement.this, R.layout.single_row_spinner, daysValues);
        mDaysAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mDaysSp.setAdapter(mDaysAdapter);

        mStatesAdapter = new ArrayAdapter<String>(DoctorOpdManagement.this, R.layout.single_row_spinner, statesValues);
        mStatesAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mStatesSp.setAdapter(mStatesAdapter);

        mCityAdapter = new ArrayAdapter<String>(DoctorOpdManagement.this, R.layout.single_row_spinner, cityValues);
        mCityAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mCitySp.setAdapter(mCityAdapter);

        selectStartTimeTv.setOnClickListener(this);
        selectEndTimeTv.setOnClickListener(this);
        createOpdProceedButton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpIds()
    {
        final Calendar cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        selectStartTimeTv = (TextView)findViewById(R.id.selectStartTimeTv);
        setStartTimeTv = (TextView)findViewById(R.id.setStartTimeTv);
        selectEndTimeTv = (TextView)findViewById(R.id.selectEndTimeTv);
        setEndTimeTv = (TextView)findViewById(R.id.setEndTimeTv);
        createOpdProceedButton = (Button)findViewById(R.id.createOPDBtn);
        mDaysSp = (Spinner)findViewById(R.id.spDays);
        mStatesSp = (Spinner)findViewById(R.id.spStates);
        mCitySp = (Spinner)findViewById(R.id.spCity);
    }

    @Override
    public void onClick(View v)
    {
       switch (v.getId()) {
           case R.id.selectStartTimeTv:
               timeFlag = 1;
               showDialog(TIME_DIALOG_ID);
               break;
           case R.id.selectEndTimeTv:
               timeFlag = 2;
               showDialog(TIME_DIALOG_ID);
               break;
           case R.id.createOPDBtn:
               finish();
               break;
       }
    }

    //CALL METHOD FOR CREATING INSTANCE OF DATE &T IME
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID: {
                return new TimePickerDialog(DoctorOpdManagement.this, mTimeSetListener, mHour, mMinute, false);
            }
        }
        return null;
    }
    // the callback received when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    if (timeFlag==1)
                    {
                        timeFlag=0;
                        setStartTimeTv.setText(Convert24to12(String.valueOf(mHour) + ":" + String.valueOf(mMinute)));
                    }else if (timeFlag==2)
                    {
                        timeFlag=0;
                        setEndTimeTv.setText(Convert24to12(String.valueOf(mHour) + ":" + String.valueOf(mMinute)));
                    }
                }};

    private String Convert24to12(String time)
    {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            final Date dateObj = sdf.parse(time);
            String formatedTime=new SimpleDateFormat("hh:mm").format(dateObj);

            String AM_PM ;
            if(mHour < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }
            return formatedTime +" "+AM_PM;
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
