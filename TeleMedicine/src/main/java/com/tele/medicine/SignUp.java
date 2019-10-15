package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.doctordashboard.HomePage;
import com.tele.medicine.pharmacydashboard.PharmacyHomePage;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;
import com.tele.medicine.utilities.SessionManager;

import org.json.JSONObject;

/**
 * Created by Database on 7/1/2016.
 */
public class SignUp extends AppCompatActivity implements LoadResponseViaGetService.AsyncTaskCompleateListener, View.OnClickListener {
    private Spinner spRoleCategory;
    private Button signUpProceedButton;
    private EditText signupNameEt, signupEmailEt, signupPasswordEt, signupConfirmPasswordeEt, signupMobileEt;
    ArrayAdapter<String> mRoleCategoryAdapter;
    int spItemPosition=0;
    SessionManager session;
    private String userTypeValue="";
    String url="", name, email, password, mNumber, confirmPassword;
    public String roleCategoryValue[] = { "Select User", "Patient", "Doctors", "Clinics", "Pharmacy Centers", "Diagonstic Center" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sign Up");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setpUpIds();
        signUpProceedButton.setOnClickListener(this);
        mRoleCategoryAdapter = new ArrayAdapter<String>(SignUp.this, R.layout.single_row_spinner, roleCategoryValue);
        mRoleCategoryAdapter.setDropDownViewResource(R.layout.single_row_spinner_bg);
        spRoleCategory.setAdapter(mRoleCategoryAdapter);
        spRoleCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                spItemPosition = spRoleCategory.getSelectedItemPosition() + 1;
                userTypeValue = parent.getItemAtPosition(position).toString();
             //   Log.e("Item Position ","POS "+spItemPosition +" "+ userTypeValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setpUpIds()
    {
        signupNameEt = (EditText)findViewById(R.id.userNameEt);
        signupEmailEt = (EditText)findViewById(R.id.input_emailEt);
        signupPasswordEt = (EditText)findViewById(R.id.passwordEt);
        signupConfirmPasswordeEt = (EditText)findViewById(R.id.confirmedPasswordEt);
        signupMobileEt = (EditText)findViewById(R.id.mobNumberEt);
        spRoleCategory = (Spinner)findViewById(R.id.spRoleCategory);
        signUpProceedButton = (Button)findViewById(R.id.signUpPreceedBtn);
    }

    @Override
    public void onClick(View v)
    {
        if (validate())
        {
            String baseURL = "http://49.50.64.226:9000/ords/consultit/registration/";
            url = baseURL+email+"/"+"/"+mNumber+"/"+password+"/"+userTypeValue;
            url = url.replaceAll(" ", "%20");
            Log.e("", "URL Formed " + url);
            LoadResponseViaGetService load = new LoadResponseViaGetService(SignUp.this);
            load.execute(url);
        }
    }

    private boolean validate()
    {
        boolean valid = true;
        name = signupNameEt.getText().toString();
        email = signupEmailEt.getText().toString();
        password = signupPasswordEt.getText().toString();
        confirmPassword = signupConfirmPasswordeEt.getText().toString();
        mNumber = signupMobileEt.getText().toString();

        if (name.isEmpty() || name.length()<3)
        {
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.error_img,0);
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Toast.makeText(getBaseContext(), "please enter at least 3 characters", Toast.LENGTH_LONG).show();
            valid = false;

        }
        else if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.error_img,0);
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Toast.makeText(getBaseContext(), "please enter valid email address", Toast.LENGTH_LONG).show();
            valid = false;
        } else if(password.isEmpty() || password.length() < 4 || password.length() > 10)
        {
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.error_img,0);
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Toast.makeText(getBaseContext(), "please enter between 4 and 10 alphanumeric characters", Toast.LENGTH_LONG).show();
            valid = false;
        }else if (!confirmPassword.contains(password))
        {
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.error_img,0);
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Toast.makeText(getBaseContext(), "password do not matched, please re enter password", Toast.LENGTH_LONG).show();
            valid = false;
        }
        else if (mNumber.isEmpty() || mNumber.length()<10 || !Patterns.PHONE.matcher(mNumber).matches())
        {
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.error_img,0);
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            Toast.makeText(getBaseContext(), "please enter 10 digit mobile number", Toast.LENGTH_LONG).show();
            valid = false;
        }

        else {
            signupNameEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupPasswordEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupConfirmPasswordeEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupEmailEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            signupMobileEt.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onTaskCompleted(String loadedResponse) {
        String response="";
        Log.e("Response", "Result Signup Received " + loadedResponse);
        if (loadedResponse!=null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught"))
        {
            try {
                JSONObject jsonObj = new JSONObject(loadedResponse);
                JSONObject childObj = jsonObj.getJSONObject("items");

                response = childObj.getString("response");
                if (response.equals("True"))
                {
                   // session.createLoginSession(email, password, name);
                        /*SharedPreferences.Editor editor=preference.edit();
                        editor.putString("SURVEYER_ID",userIdEt.getText().toString());
                        editor.putString("SURVEYER_PASS",passwordEt.getText().toString());
                        editor.putString("SURVEYER_EMAIL",userEmail);
                        editor.putString("SURVEYER_NAME",userName).commit();*/
                    if (spItemPosition==2) {
                        startActivity(new Intent(SignUp.this, HomeScreen.class));
                        finish();
                        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                        Toast.makeText(this, "sucessfully registered ", Toast.LENGTH_LONG).show();
                    }else if (spItemPosition==3) {
                        startActivity(new Intent(SignUp.this, HomePage.class));
                        finish();
                        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                    }/*else if (spItemPosition==5)
                    {
                        startActivity(new Intent(SignUp.this, PharmacyHomePage.class));
                        finish();
                        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                    }*/else
                    {
                        Toast.makeText(getBaseContext(), "please select user type", Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(this, "please enter correct id and password ", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(SignUp.this))
            {
                Toast.makeText(this, "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }
        }
    }
}
