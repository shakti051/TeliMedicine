package com.tele.medicine.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.tele.medicine.HomeScreen;
import com.tele.medicine.LoginScreen;

import java.util.HashMap;

/**
 * Created by Database on 8/5/2016.
 */
public class SessionManager
{
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    Context _context;
    //Shared Preferences file name
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String USER_EMAIL_ID = "userid";
    public static final String USER_PASS = "password";

    public SessionManager(Context context) {
        this._context = context;
        preference = _context.getSharedPreferences("TM_Preference", _context.MODE_PRIVATE);
        editor = preference.edit();
    }
    public void createLoginSession(String userId, String password) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_EMAIL_ID, userId);
        editor.putString(USER_PASS, password);
       // editor.putString("USER_NAME", userName);
        editor.commit();
    }

    public void checkLogin() {
        if (!isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            _context.startActivity(new Intent(_context, LoginScreen.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        else
        {
            _context.startActivity(new Intent(_context, HomeScreen.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
    // Get stored session data
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(USER_EMAIL_ID, preference.getString(USER_EMAIL_ID, null));
        user.put(USER_PASS, preference.getString(USER_PASS, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        // After logout redirect user to Loging Activity
        _context.startActivity(new Intent(_context, LoginScreen.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    // Get Login State
    public boolean isLoggedIn() {
        return preference.getBoolean(IS_LOGIN, false);
    }
}
