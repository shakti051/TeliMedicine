package com.tele.medicine;

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
import android.widget.RelativeLayout;

import com.tele.medicine.adapterview.SearchDoctorSpecialityAdapter;
import com.tele.medicine.utilities.ProjectConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Database on 7/13/2016.
 */
public class LocationSearch extends AppCompatActivity implements SearchDoctorSpecialityAdapter.OnSearchClickListener
{
    private RecyclerView mRecyclerView;
    private String currentLocation;
    private EditText toolBarSearchEt;
    SearchDoctorSpecialityAdapter mAdapter;
    RelativeLayout locationSearchLayout;
    List<String> list;
    ArrayList<String> arrayList;

    SearchDoctorSpecialityAdapter.OnSearchClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);
        Toolbar toolbar = (Toolbar)findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);



        Bundle b = getIntent().getExtras();
        if (b!=null)
        {
            currentLocation = b.getString("CURRENT_LOCATION");
//            arrayList = new ArrayList<HashMap<String, String>>();
            arrayList =  b.getStringArrayList("LOCATION_LIST");
            Log.e("CheckArrayList","CheckArrayList"+arrayList);
        }


        locationSearchLayout= (RelativeLayout)findViewById(R.id.locationSearchView);
        locationSearchLayout.setVisibility(View.VISIBLE);

        toolBarSearchEt = (EditText)findViewById(R.id.searchLocationView);
        toolBarSearchEt.setText(currentLocation);
        toolBarSearchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolBarSearchEt.setText("");
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.doctorSpecialityRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //createLocationListValue(); // method for location list
        mAdapter = new SearchDoctorSpecialityAdapter(arrayList, LocationSearch.this, "LOCATION_SEARCH");
        mRecyclerView.setAdapter(mAdapter);
        locationSearchListener();  // method for location search

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /*private void createLocationListValue()    //method for location list
    {
        list = new ArrayList<String>();
        list.add("Delhi");
        list.add("Noida");
        list.add("Ghaziabad");
        list.add("Mumbai");
        list.add("Chandigarh");
        list.add("Hyderabad");
        list.add("Pune");
        list.add("Varanasi");
        list.add("Gurgaon");
        list.add("Kerla");
    }*/

    private void locationSearchListener()   // method for location search
    {
        toolBarSearchEt.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                final List<String> filteredList = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    final String text = arrayList.get(i).toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(arrayList.get(i));
                    }
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(LocationSearch.this));
                mAdapter = new SearchDoctorSpecialityAdapter(filteredList, LocationSearch.this, "LOCATION_SEARCH");
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onSearchClick(String locationName) {

        Log.e("LSChechLocation","checkLocation..."+locationName);

        ProjectConstant.selectedLocation=locationName;

        Log.e("Selected LocationLS  ","selected locationls  "+locationName);
        toolBarSearchEt.setText(locationName);
        LocationSearch.this.finish();

    }

}
