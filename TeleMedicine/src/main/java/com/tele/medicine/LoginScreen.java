package com.tele.medicine;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
//import com.facebook.login.widget.LoginButton;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.linkedin.platform.utils.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.AuthListener;
import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.doctordashboard.HomePage;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;
import com.tele.medicine.utilities.SessionManager;
import org.json.JSONObject;

import java.util.Arrays;
import com.google.android.gms.auth.api.Auth;


/**
 * Created by Database on 7/1/2016.
 */
public class LoginScreen extends AppCompatActivity implements LoadResponseViaGetService.AsyncTaskCompleateListener,
        View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Button loginProceedButton, signUpProceedButton;
    private TextView skipProceedButton;
    SharedPreferences preference;
    private EditText userIdEt, userPasswordEt;
    SessionManager session;
    String url="", baseURL;

    private ImageView login_linkedin_btn;
    private LoginButton loginBtn;
    private ImageView signinButton;

//    private TextView username,emailID,fbID;
    private UiLifecycleHelper uiHelper;
    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;
    private boolean signedInUser;
    private ConnectionResult mConnectionResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.login_screen);

        setUpIds();
        loginBtn.setReadPermissions(Arrays.asList("email"));
        session = new SessionManager(LoginScreen.this);


        baseURL = "http://49.50.64.226:9000/ords/consultit/usertype/";

        loginProceedButton.setOnClickListener(this);
        signUpProceedButton.setOnClickListener(this);
        skipProceedButton.setOnClickListener(this);

        loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {
                   /*username.setText("You are currently logged in as " + user.getName());
                    fbID.setText("Your Facebook Id is " + user.getId());
                    emailID.setText("email is " + user.getProperty("email"));
                    String email = user.getProperty("email").toString();
                    emailID.setText(email);*/
                    Log.e("USERNAME", "username..." + user.getName());
                    Log.e("FACEBOOKID", "facebookid..." + user.getId());
                    Log.e("EMAILID", "emailid..." + user.getProperty("email"));
                    Log.e("GENDER", "gender..." + user.getProperty("gender"));
                } else {
//                    username.setText("You are not logged in.");
                    Toast.makeText(LoginScreen.this, "You are not logged in", Toast.LENGTH_LONG).show();
                }
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                googlePlusLogin();
                if (mGoogleApiClient.isConnected()) {
                    Toast.makeText(LoginScreen.this, "You are already signin", Toast.LENGTH_LONG).show();
//                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                } else {
                    googlePlusLogin();
                }
            }
        });

        login_linkedin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mGoogleApiClient = buildGoogleAPIClient();
    }

    private GoogleApiClient buildGoogleAPIClient() {
        return new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    // Authenticate with linkedin and intialize Session.

    public void login(){
        LISessionManager.getInstance(getApplicationContext()).init(this, buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {

                Toast.makeText(getApplicationContext(), "success" + LISessionManager.getInstance(getApplicationContext()).getSession().getAccessToken().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAuthError(LIAuthError error) {

                Toast.makeText(getApplicationContext(), "failed " + error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }, true);
    }

    /*// After complete authentication start new HomePage Activity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this,
                requestCode, resultCode, data);
        Intent intent = new Intent(LoginScreen.this, HomePage.class);
        startActivity(intent);
    }*/


    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void googlePlusLogin() {  //gp
        if (!mGoogleApiClient.isConnecting()) {
            signedInUser = true;
            resolveSignInError();
        }
    }

    private void resolveSignInError() {  //gp
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            }
            catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }


    private Session.StatusCallback statusCallback = new Session.StatusCallback() {// fb
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            if (state.isOpened()) {
                Log.d("LoginScreen", "Facebook session opened.");
            } else if (state.isClosed()) {
                Log.d("LoginScreen", "Facebook session closed.");
            }
        }
    };

    private void setUpIds()
    {
        preference = getSharedPreferences("TM_Preference", MODE_PRIVATE);
        userIdEt = (EditText)findViewById(R.id.input_email);
        userPasswordEt = (EditText)findViewById(R.id.input_password);
        loginProceedButton = (Button)findViewById(R.id.btn_login);
        signUpProceedButton = (Button)findViewById(R.id.registerBtn);
        skipProceedButton = (TextView)findViewById(R.id.skipLoginTv);

        signinButton = (ImageView) findViewById(R.id.gp_signin);// google signin
        loginBtn = (LoginButton) findViewById(R.id.login_button);// facebook login
        login_linkedin_btn = (ImageView)findViewById(R.id.ln_login_button);// linkedIn
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_login:
                if(userIdEt.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginScreen.this, "please enter the user id", Toast.LENGTH_LONG).show();
                }else if (userPasswordEt.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginScreen.this, "please enter the password", Toast.LENGTH_LONG).show();
                }else {
                    url = baseURL+userIdEt.getText().toString()+"/"+userPasswordEt.getText().toString();
                    url = url.replaceAll(" ", "%20");
                    Log.e("", "URL Formed " + url);
                    LoadResponseViaGetService load = new LoadResponseViaGetService(LoginScreen.this);
                    load.execute(url);
                }
                break;
            case R.id.registerBtn:
                startActivity(new Intent(LoginScreen.this, SignUp.class));
                overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                break;
            case R.id.skipLoginTv:
                startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                finish();
                break;
        }
    }

    @Override
    public void onTaskCompleted(String loadedResponse) {
        String response="", userType="";
        Log.e("Login Response", "Result loginReceived " + loadedResponse);
        if (loadedResponse!=null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught"))
        {
            try {
                JSONObject jsonObj = new JSONObject(loadedResponse);
                JSONObject childObj = jsonObj.getJSONObject("items");

                response = childObj.getString("response");
                if (response.equals("True"))
                {
                    userType = childObj.getString("usertype");
                      if (userType.equals("Patient"))
                      {
                          session.createLoginSession(userIdEt.getText().toString(), userPasswordEt.getText().toString());

                          Log.e("Lagin value", "Pa.aaya... "+ userIdEt.getText().toString()+ " "+ userPasswordEt.getText().toString());
                          startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                          overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                          finish();
                      }else if(userType.equals("Doctors"))
                      {
//                          session.createLoginSessionDo(userIdEt.getText().toString(), userPasswordEt.getText().toString());

                          Log.e("Login value","Dr.aaya... " +userIdEt.getText().toString()+ " " +userPasswordEt.getText().toString());
                          startActivity(new Intent(LoginScreen.this, HomePage.class));
                          overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
                          finish();
                      }
                   // userName = childObj.getString("name");
                        /*SharedPreferences.Editor editor=preference.edit();
                        editor.putString("SURVEYER_ID",userIdEt.getText().toString());
                        editor.putString("SURVEYER_PASS",passwordEt.getText().toString());
                        editor.putString("SURVEYER_EMAIL",userEmail);
                        editor.putString("SURVEYER_NAME",userName).commit();*/

                }else if(response.equals("false"))
                {
                    Toast.makeText(this, "please enter correct id and password ", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(LoginScreen.this))
            {
                Toast.makeText(this, "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) { //gp
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    signedInUser = false;
                }
                mIntentInProgress = false;
                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
        }

        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this,
                requestCode, resultCode, data);   //Ln
    }

    // This method is used to make permissions to retrieve data from linkedin

    private static Scope buildScope() {  // Ln
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }

    @Override
    public void onConnected(Bundle bundle) { //gp
        signedInUser = false;
        Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_LONG).show();
        getProfileInformation();
    }

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String dateofbirth = currentPerson.getBirthday();
                int gender = currentPerson.getGender();
                //String personPhotoUrl = currentPerson.getImage().getUrl();
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);


                Log.e("PERSONNAME","personname... "+personName);
                Log.e("DATEOFBIRTH","dateofbirth... "+dateofbirth);
                Log.e("GENDER","gender... "+gender);
                Log.e("EMAIL","email... "+email);


                /*username.setText(personName);
                emailLabel.setText(email);
                Log.e("yyyy", "nnn" + dobTV);
                dobTV.setText(dateofbirth);
                Log.e("gggg", "mmmm" + gender);
                genderTV.setText(gender);*/

                //new LoadProfileImage(image).execute(personPhotoUrl);

                // update profile frame with new info about Google Account
                // profile
                updateProfile(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
        updateProfile(false);
    }

    private void updateProfile(boolean isSignedIn) {
        if (isSignedIn) {
            /*signinFrame.setVisibility(View.GONE);
            profileFrame.setVisibility(View.VISIBLE);*/

        } else {
            /*signinFrame.setVisibility(View.VISIBLE);
            profileFrame.setVisibility(View.GONE);*/
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            // store mConnectionResult
            mConnectionResult = connectionResult;

            if (signedInUser) {
                resolveSignInError();
            }
        }
    }


}
