package com.tele.medicine.hometabfragment;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.DrSuggestPathLogy;
import com.tele.medicine.PathoLogySearchResult;
import com.tele.medicine.PharmacySearchResult;
import com.tele.medicine.R;
import com.tele.medicine.SearchResultActivity;
import com.tele.medicine.SpecialityWiseDoctorsScreen;
import com.tele.medicine.adapterview.GridViewSearchAdapter;
import com.tele.medicine.adapterview.SearchDoctorSpecialityAdapter;
import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Database on 6/29/2016.
 */
public class HomeTabFragment extends Fragment implements View.OnClickListener, LoadResponseViaGetService.AsyncTaskCompleateListener
{
    ArrayList<HashMap<String, String>> specialitylistValue;
    private GridView gridview;
    private LinearLayout findDoctorsProceedLayout, findPathLabProceedLayout, findPharmacyProceedLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_tab_fragment, container, false);

        setUpIds(view);
        gridview = (GridView) view.findViewById(R.id.gridview);

        String baseURL = "http://49.50.64.226:9000/ords/consultit/speciality/a/";
        // url = url.replaceAll(" ", "%20");
        Log.e("Speciality URL.. ", "Home URL Formed " + baseURL);
        LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(getActivity(), this);
        loadAsyncTask.execute(baseURL);
        return view;
    }

    private void setUpIds(View view)
    {
        findDoctorsProceedLayout = (LinearLayout)view.findViewById(R.id.findDoctorsLayout);
        findPathLabProceedLayout = (LinearLayout)view.findViewById(R.id.pathLabTestLayout);
        findPharmacyProceedLayout = (LinearLayout)view.findViewById(R.id.findpharmacyLayout);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findDoctorsProceedLayout.setOnClickListener(this);
        findPathLabProceedLayout.setOnClickListener(this);
        findPharmacyProceedLayout.setOnClickListener(this);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.gridTextViewID);
                String name = textView.getText().toString();
                Intent i = new Intent(getActivity(), SpecialityWiseDoctorsScreen.class);
                i.putExtra("VIEW_NAME", name);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.findDoctorsLayout:
                Intent i = new Intent(getActivity(), SearchResultActivity.class);
                startActivity(i);
                break;
            case R.id.findpharmacyLayout:
                Intent pharmacyIntent = new Intent(getActivity(), PharmacySearchResult.class);
                startActivity(pharmacyIntent);
                break;
            case R.id.pathLabTestLayout:
                Intent pathLab = new Intent(getActivity(), DrSuggestPathLogy.class);
                startActivity(pathLab);
                break;
        }
    }

    @Override
    public void onTaskCompleted(String loadedResponse)
    {
        specialitylistValue = new ArrayList<HashMap<String, String>>();
        Log.e("Speciality Result ", "Result " + loadedResponse);
        if (loadedResponse != null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught"))
        {
            try {
                JSONObject jsonObj = new JSONObject(loadedResponse);
                JSONArray arrObj = jsonObj.getJSONArray("items");

                for (int i = 0; i< arrObj.length(); i++) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("SPECIALITY", arrObj.getJSONObject(i).getString("speciality"));
                    specialitylistValue.add(hashMap);
                }
                gridview.setAdapter(new GridViewSearchAdapter(getActivity(), specialitylistValue));

            } catch (Exception e) {
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(getActivity())) {
                Toast.makeText(getActivity(), "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }
        }
    }
}
