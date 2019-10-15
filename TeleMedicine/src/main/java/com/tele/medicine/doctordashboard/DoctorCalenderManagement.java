package com.tele.medicine.doctordashboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tele.medicine.R;
import com.tele.medicine.doctoradapterview.DoctorAppointmentFixedAdapter;
import com.tele.medicine.doctoradapterview.DoctorCalenderDaysListAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Database on 7/16/2016.
 */
public class DoctorCalenderManagement extends AppCompatActivity implements View.OnClickListener {
    private TextView selectFromDateTv, setFromDateTv, selectToDateTv, setToDateTv, selectStartTimeTv, setStartTimeTv, selectEndTimeTv, setEndTimeTv;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;
    int dateFlag = 0, timeFlag = 0;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DoctorCalenderDaysListAdapter mAdapter;
    private int pYear, pMonth, pDay, mHour, mMinute, amPM;
    private Button submitButton;
    private Spinner spTimeSlot;
    ArrayAdapter<String> mTimeSlotAdapter;
    public String timeSlotValue[] = { "00 min", "10 min", "15 min", "20 min", "25 min"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_calender_managment);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Calender Management");
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.calenderDaysMgmtRV);
        layoutManager = new LinearLayoutManager(DoctorCalenderManagement.this);
        mRecyclerView.setLayoutManager(layoutManager);

        setUpIds();
        selectFromDateTv.setOnClickListener(this);
        selectToDateTv.setOnClickListener(this);
        selectStartTimeTv.setOnClickListener(this);
        selectEndTimeTv.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        mTimeSlotAdapter = new ArrayAdapter<String>(DoctorCalenderManagement.this, R.layout.single_row_spinner, timeSlotValue);
        mTimeSlotAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spTimeSlot.setAdapter(mTimeSlotAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpIds()
    {
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        selectFromDateTv = (TextView)findViewById(R.id.selectFromDateTv);
        setFromDateTv = (TextView)findViewById(R.id.setFromDateTv);
        selectToDateTv = (TextView)findViewById(R.id.selectToDateTv);
        setToDateTv = (TextView)findViewById(R.id.setToDateTv);
        selectStartTimeTv = (TextView)findViewById(R.id.selectStartTimeTv);
        setStartTimeTv = (TextView)findViewById(R.id.setStartTimeTv);
        selectEndTimeTv = (TextView)findViewById(R.id.selectEndTimeTv);
        setEndTimeTv = (TextView)findViewById(R.id.setEndTimeTv);
        spTimeSlot = (Spinner)findViewById(R.id.spTimeSlot);
        submitButton = (Button)findViewById(R.id.calMgmtPreceedBtn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.selectFromDateTv:
                dateFlag = 1;
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.selectToDateTv:
                dateFlag = 2;
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.selectStartTimeTv:
                timeFlag = 1;
                showDialog(TIME_DIALOG_ID);
                break;
            case R.id.selectEndTimeTv:
                timeFlag = 2;
                showDialog(TIME_DIALOG_ID);
                break;
            case R.id.calMgmtPreceedBtn:
               finish();
                break;
        }
    }

    //CALL METHOD FOR CREATING INSTANCE OF DATE &T IME
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(DoctorCalenderManagement.this, pDateSetListener, pYear, pMonth, pDay);
            case TIME_DIALOG_ID: {
                return new TimePickerDialog(DoctorCalenderManagement.this, mTimeSetListener, mHour, mMinute, false);
            }
        }
        return null;
    }

    //For date Callbavk method
    private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            pYear = year;
            pMonth = monthOfYear+1;
            pDay = dayOfMonth;
            String formate_date_string;
            DateFormat dformate;
            SimpleDateFormat formatter;

            formate_date_string = (pDay + "-" + (pMonth) + "-" + pYear);
            dformate = new SimpleDateFormat("dd-MM-yyyy");
            formatter = new SimpleDateFormat("dd-MM-yyyy");

            if (dateFlag==1)
            {
                dateFlag=0;
                Date formatedDate = null;
                try {
                    formatedDate = dformate.parse(formate_date_string);
                    String formatedDateString = formatter.format(formatedDate);
                    setFromDateTv.setText(formatedDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else if (dateFlag==2)
            {
                dateFlag=0;
                Date formatedDate = null;
                try {
                    formatedDate = dformate.parse(formate_date_string);
                    String formatedDateString = formatter.format(formatedDate);
                    setToDateTv.setText(formatedDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                /*setToDateTv.setText(new StringBuilder()
                        .append(pDay).append("-")
                        .append(pMonth + 1).append("-")
                        .append(pYear));//.append(" "));*/

               if (setFromDateTv!=null && setToDateTv!=null)
               {
                   ArrayList<String> dates = getDates(setFromDateTv.getText().toString(), setToDateTv.getText().toString());
                   for(String date:dates) {
                       System.out.println("Aayaaa "+date);
                   }
                   mAdapter = new DoctorCalenderDaysListAdapter(DoctorCalenderManagement.this, dates);
                   mRecyclerView.setAdapter(mAdapter);
               }else
               {
                     Toast.makeText(DoctorCalenderManagement.this, "Plese insert from and to date = ", Toast.LENGTH_SHORT).show();
               }
            }
        }
    };

    private ArrayList<String> getDates(String fromDate, String toDate)
    {
        ArrayList<String> dates = new ArrayList<String>();
        DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(fromDate);
            date2 = df1 .parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = sdf.format(cal1.getTime());           //Date
            SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");      //Day
            String dayName = sdf_.format(cal1.getTime());

            dates.add(dayName+" "+currentDate);
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    // the callback received when the user "sets" the time in the dialog
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
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

                }
            };

    private String Convert24to12(String time)
    {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            final Date dateObj = sdf.parse(time);
            String formatedTime=new SimpleDateFormat("hh:mm").format(dateObj);
            //hourTime=new SimpleDateFormat("hh").format(dateObj);
            //Log.e("TIME_HOUR.", "Hourrr  " + hourTime);
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



}
