package com.tele.medicine.asynctaskservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.tele.medicine.doctorlisttabfragment.ClinicAppointmentsTabFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Database on 8/5/2016.
 */
public class LoadResponseViaGetService extends AsyncTask<String,Void,String> {
    Context activityContext;
    private ProgressDialog mProgressDialog;
    private AsyncTaskCompleateListener mTaskCompleateListener;
    String webResponse = "";
    URL downloadUrl = null;
    int a;
    HttpURLConnection connection = null;
    InputStream inputStream = null;
    int read = -1;
    byte[] buffer;
    boolean isProgressEnable = true;

    public LoadResponseViaGetService(Context context) {
        this.activityContext = context;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage(" Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mTaskCompleateListener = (AsyncTaskCompleateListener) activityContext;
    }

    public LoadResponseViaGetService(Context context, AsyncTaskCompleateListener mTaskCompleateListener)
    {
        this.activityContext = context;
        mProgressDialog = new ProgressDialog(activityContext);
        mProgressDialog.setMessage(" Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        this.mTaskCompleateListener = mTaskCompleateListener;

    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        if (mProgressDialog != null && isProgressEnable)
            mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... url)
    {
        try {
            downloadUrl=new URL(url[0]);
            Log.e("Request", "URL formed " + url[0]);
            connection = (HttpURLConnection) downloadUrl.openConnection();
            //  connection.setConnectTimeout(3000);
            inputStream = connection.getInputStream();
            buffer = new byte[1024];

            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            webResponse = total.toString();
        } catch (Exception e) {
            webResponse = "ExceptionCaught";
            Log.e("Exception ", "aayaaa");
            // e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }
            }
        }

        return webResponse;
    }
    @Override
    protected void onPostExecute(String receivedString) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.cancel();
        mTaskCompleateListener.onTaskCompleted(receivedString);
    }

    public interface AsyncTaskCompleateListener {
        public void onTaskCompleted(String loadedResponse);
    }
}
