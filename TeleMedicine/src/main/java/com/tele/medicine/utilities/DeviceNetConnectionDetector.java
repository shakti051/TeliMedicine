package com.tele.medicine.utilities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Database on 8/5/2016.
 */
public class DeviceNetConnectionDetector
{
    public static boolean checkDataConnWifiMobile(Activity activityContext)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)activityContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected())
            {
                return true;
            }
        }catch(Exception networkException)
        {
        }
        return false;
    }
}
