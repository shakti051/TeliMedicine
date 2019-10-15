package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tele.medicine.adapterview.GridViewSearchAdapter;
import com.tele.medicine.adapterview.SearchDoctorSpecialityAdapter;
import com.tele.medicine.asynctaskservices.LoadResponseViaGetService;
import com.tele.medicine.utilities.DeviceNetConnectionDetector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Database on 6/30/2016.
 */
public class SearchResultActivity extends AppCompatActivity implements SearchDoctorSpecialityAdapter.OnSearchClickListener, LoadResponseViaGetService.AsyncTaskCompleateListener
{
    ArrayList<HashMap<String, String>> listValue;
    private RecyclerView mRecyclerView;
    private EditText searchSpecialityET;
    SearchDoctorSpecialityAdapter mAdapter;
    List<String> list;
    RelativeLayout doctorSpecialtySearchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        Toolbar toolbar = (Toolbar)findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        doctorSpecialtySearchLayout= (RelativeLayout)findViewById(R.id.specialitySearchView);
        doctorSpecialtySearchLayout.setVisibility(View.VISIBLE);

        searchSpecialityET = (EditText)findViewById(R.id.searchView);
        searchSpecialityET.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         /*Intent i = getIntent();
        int position = i.getExtras().getInt("ID");
        GridViewSearchAdapter gridViewAdapter = new GridViewSearchAdapter(this);
        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(gridViewAdapter.mThumbIds[position]);*/
        createListValue();
    }
    private void createListValue()
    {
        String baseURL = "http://49.50.64.226:9000/ords/consultit/speciality/a/";
       // url = url.replaceAll(" ", "%20");
        Log.e("Speciality URL.. ", "URL Formed " + baseURL);
        LoadResponseViaGetService loadAsyncTask = new LoadResponseViaGetService(SearchResultActivity.this);
        loadAsyncTask.execute(baseURL);

       /* list = new ArrayList<String>();
        list.add("Dermatologist");
        list.add("Dentist");
        list.add("General Physician");
        list.add("Gynecologist");
        list.add("HomeoPathy");
        list.add("Psycologist");
        list.add("Psychiatrist");
        list.add("Ayurveda");
        list.add("Sexologist");*/
    }

    private void specialitySearchListener()
    {
        searchSpecialityET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                ArrayList<HashMap<String, String>> filteredList = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < listValue.size(); i++) {
                    final String text = listValue.get(i).get("SPECIALITY").toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(listValue.get(i));
                    }
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                mAdapter = new SearchDoctorSpecialityAdapter(filteredList, SearchResultActivity.this, "DOCTOR_SEARCH_SPECIALTY");
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchSpecialityET.setText("");
    }

    @Override
    public void onSearchClick(String specialityName)
    {
       Intent intent = new Intent(SearchResultActivity.this, SpecialityWiseDoctorsScreen.class);
       intent.putExtra("SPECIALITY_NAME", specialityName);
       startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onTaskCompleted(String loadedResponse)
    {
        listValue = new ArrayList<HashMap<String, String>>();
        Log.e("Speciality Result ", "Result " + loadedResponse);
        if (loadedResponse != null && !loadedResponse.equals("") && !loadedResponse.equals("ExceptionCaught"))
        {
            try {
                JSONObject jsonObj = new JSONObject(loadedResponse);
                JSONArray arrObj = jsonObj.getJSONArray("items");

                for (int i = 0; i< arrObj.length(); i++) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("SPECIALITY", arrObj.getJSONObject(i).getString("speciality"));
                    listValue.add(hashMap);
                }
                mAdapter = new SearchDoctorSpecialityAdapter(listValue, SearchResultActivity.this, "DOCTOR_SEARCH_SPECIALTY");
                mRecyclerView.setAdapter(mAdapter);
                specialitySearchListener();
                } catch (Exception e) {
            }
        } else {
            if (DeviceNetConnectionDetector.checkDataConnWifiMobile(SearchResultActivity.this)) {
                Toast.makeText(this, "Unable to connect to server. Please try again ", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please cheack your internet connection ", Toast.LENGTH_LONG).show();
            }
        }
    }
}