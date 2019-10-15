package com.tele.medicine;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.Session;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;


/**
 * Created by Database on 9/7/2016.
 */
public class Login extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //**********************************************************************************//
    // VARIABLES DEFINING FOR GOOGLE PLUS INTEGRATION								    //
    //**********************************************************************************//
    private static final int GPLUS_SIGN_IN_REQUEST_CODE = 22;
    private static final int ERROR_DIALOG_REQUEST_CODE = 23;
    // For communicating with Google APIs
    private GoogleApiClient mGoogleApiClient;
    private boolean mSignInClicked;
    private boolean mIntentInProgress;
    // Google Play services
    private ConnectionResult mConnectionResult;
//    private boolean isGPlusLoginCalled=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient=buildGoogleAPIClient();

        processGPlusSignIn();

    }

    /**
     * API to return GoogleApiClient Make sure to create new after revoking
     * access or for first time sign in
     */
    private GoogleApiClient buildGoogleAPIClient() {
        return new GoogleApiClient.Builder(this).addConnectionCallbacks(Login.this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPLUS_SIGN_IN_REQUEST_CODE)
        {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            // Uncomment to use deprecated way of finding Person's information
            if (!mGoogleApiClient.isConnecting())
            {
                mGoogleApiClient.connect();
            }
        }
    }


    @Override
    public void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    /**
     * API to handler sign in of user If error occurs while connecting process
     * it in processSignInError() api
     */
    private void processGPlusSignIn() {

        if (!mGoogleApiClient.isConnecting())
        {
            startExecutingGPlusLoginProcess();
            mSignInClicked = true;
        }
    }

    /**
     * Callback for GoogleApiClient connection failure
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onConnectionFailed(ConnectionResult result)
    {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, ERROR_DIALOG_REQUEST_CODE).show();
            return;
        }
        if (!mIntentInProgress) {
            mConnectionResult = result;
            if (mSignInClicked) {
                startExecutingGPlusLoginProcess();
            }
        }
    }

    private void startExecutingGPlusLoginProcess()
    {
        if (mConnectionResult != null && mConnectionResult.hasResolution())
        {
            try
            {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this,GPLUS_SIGN_IN_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    /**
     * Callback for GoogleApiClient connection success
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        mSignInClicked = false;
        Toast.makeText(getApplicationContext(), "Signed In Successfully", Toast.LENGTH_LONG).show();

        getGPlusUserInformation();

    }

    @SuppressWarnings("deprecation")
    private void getGPlusUserInformation() {
        // TODO Auto-generated method stub
        Person signedInUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        if (signedInUser != null)
        {
            String firstName="",lastName="";

            if (signedInUser.hasDisplayName())
            {
                Person.Name googleName = signedInUser.getName();
                firstName=googleName.getGivenName();
                lastName=googleName.getFamilyName();
            }

            int gender = signedInUser.getGender();
        }

    }

    /**
     * Callback for suspension of current connection
     */
    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();

    }


}
