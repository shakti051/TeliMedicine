package com.tele.medicine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.tele.medicine.utilities.SessionManager;

/**
 * Created by Database on 7/6/2016.
 */
public class UserProfileDetails extends AppCompatActivity implements View.OnClickListener {
    private Button editProceedButton, saveProceedButton, userFamilySaveProceedBtn;
    private EditText userNameEt,userAgeEt,mobNumberEt, emailEt,addressEt;
    private Spinner mGenderSp, mfamilymemberGenderSp, mfamilyRelationshipSp;
    private LinearLayout userLayoutView, userFamilyLayoutView;
    private TextView fromButtomSlideUpTv, fromTopSlideBottomTv;
    SharedPreferences preference;

    ArrayAdapter<String> mGenderAdapter, mMemberRelationshipAdapter;
    public String genderValue[] = { "Gender ", "Male ", "Female "};
    public String memberRelationshipValue[] = { "Relationship", "Father ", "Brother ", "Sister ", "Uncle "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_details);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Details");
        setSupportActionBar(toolbar);

        setUpIds();
        mGenderAdapter = new ArrayAdapter<String>(UserProfileDetails.this, R.layout.single_row_spinner, genderValue);
        mGenderAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mGenderSp.setAdapter(mGenderAdapter);
        mfamilymemberGenderSp.setAdapter(mGenderAdapter);

        mMemberRelationshipAdapter = new ArrayAdapter<String>(UserProfileDetails.this, R.layout.single_row_spinner, memberRelationshipValue);
        mMemberRelationshipAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        mfamilyRelationshipSp.setAdapter(mMemberRelationshipAdapter);

        editProceedButton.setOnClickListener(this);
        fromButtomSlideUpTv.setOnClickListener(this);
        fromTopSlideBottomTv.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpIds()
    {
        preference = getSharedPreferences("TM_Preference", Context.MODE_PRIVATE);
        userLayoutView = (LinearLayout)findViewById(R.id.userDetailsLayout);
        userFamilyLayoutView = (LinearLayout)findViewById(R.id.userFamilyLayout);
        editProceedButton = (Button)findViewById(R.id.editProceedBtn);
        saveProceedButton = (Button)findViewById(R.id.saveProceedBtn);
        userFamilySaveProceedBtn = (Button)findViewById(R.id.userFamilySaveProceedBtn);
        userNameEt = (EditText)findViewById(R.id.userNameEt);
        userAgeEt = (EditText)findViewById(R.id.ageEt);
        emailEt = (EditText)findViewById(R.id.input_emailEt);
        addressEt = (EditText)findViewById(R.id.addressEt);
        mobNumberEt = (EditText)findViewById(R.id.mobNumberEt);
        fromButtomSlideUpTv = (TextView)findViewById(R.id.fromButtomSlideUpTv);
        fromTopSlideBottomTv = (TextView)findViewById(R.id.fromTopSlideDownTv);
        mGenderSp = (Spinner)findViewById(R.id.spGender);
        mfamilyRelationshipSp = (Spinner)findViewById(R.id.spfamilyRelation);
        mfamilymemberGenderSp = (Spinner)findViewById(R.id.spfamilyMemberGender);

        String userEMailId = preference.getString("USER_EMAIL_ID", "nodata");
        emailEt.setText(userEMailId);
    }

    @Override
    public void onClick(View v)
    {
      switch (v.getId())
      {
          case R.id.editProceedBtn:
          {
              fromButtomSlideUpTv.setVisibility(View.VISIBLE);
              editProceedButton.setVisibility(View.GONE);
              saveProceedButton.setVisibility(View.VISIBLE);
              userNameEt.setEnabled(true);
              userNameEt.setCursorVisible(true);
              userAgeEt.setEnabled(true);
              emailEt.setEnabled(true);
              addressEt.setEnabled(true);
              mobNumberEt.setEnabled(true);
              saveProceedButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      finish();
                  }
              });
              break;
          }

          case R.id.fromButtomSlideUpTv:
          {
              fromButtomSlideUpTv.setVisibility(View.GONE);
              Animation bottomUp = AnimationUtils.loadAnimation(UserProfileDetails.this,R.anim.slide_up);
              ViewGroup familyMenber = (ViewGroup)findViewById(R.id.userFamilyLayout);
              familyMenber.startAnimation(bottomUp);
              userLayoutView.setVisibility(View.GONE);
              familyMenber.setVisibility(View.VISIBLE);
              fromTopSlideBottomTv.setVisibility(View.VISIBLE);
              familySaveProceedMerhod();
              break;
          }
          case R.id.fromTopSlideDownTv:
          {
              fromTopSlideBottomTv.setVisibility(View.GONE);
              Animation UpBottom = AnimationUtils.loadAnimation(UserProfileDetails.this,R.anim.slide_down);
              ViewGroup userDetailsView = (ViewGroup)findViewById(R.id.userDetailsLayout);
              userDetailsView.startAnimation(UpBottom);
              userFamilyLayoutView.setVisibility(View.GONE);
              userDetailsView.setVisibility(View.VISIBLE);
              fromButtomSlideUpTv.setVisibility(View.VISIBLE);
             // familySaveProceedMerhod();
              break;
          }
        }
    }

    private void familySaveProceedMerhod()
    {
        userFamilySaveProceedBtn.setVisibility(View.VISIBLE);
        userFamilySaveProceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
