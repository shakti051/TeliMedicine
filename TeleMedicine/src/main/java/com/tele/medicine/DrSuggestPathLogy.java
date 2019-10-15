package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tele.medicine.pathologyadapterview.SuggestAndPathologySearchResultAdapter;
import com.tele.medicine.utilities.GetLocationService;

/**
 * Created by Database on 9/1/2016.
 */
public class DrSuggestPathLogy extends AppCompatActivity implements View.OnClickListener,SuggestAndPathologySearchResultAdapter.OnClickListener {

    TextView nearByLabsTV;
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GetLocationService appLocationService;
    double mLatitude = 0;
    double mLongitude = 0;
    boolean isLocationSetting=false;
    String ciyt="";
    SuggestAndPathologySearchResultAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dr_suggest_pathology);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Suggest Pathology Result");
        setSupportActionBar(toolbar);

        setUpIds();
        nearByLabsTV.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(DrSuggestPathLogy.this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new SuggestAndPathologySearchResultAdapter(DrSuggestPathLogy.this);
        mRecyclerView.setAdapter(mAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getlocationOnOffStaus();

    }

    private void setUpIds() {
        nearByLabsTV = (TextView)findViewById(R.id.nearLabLocationID);
        mRecyclerView = (RecyclerView)findViewById(R.id.suggestPatholagyRVID);
    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this,PathoLogySearchResult.class);
        startActivity(intent);
    }

    @Override
    public void onCardClick(int position, String pathologyName, String address) {

        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
        Intent intent = new Intent(DrSuggestPathLogy.this, PathologyTestViewDetails.class);
        intent.putExtra("PATHOLOGY_NAME", pathologyName);
        intent.putExtra("PATHOLOGY_LOCATION", address);
        intent.putExtra("FROM_PATHOLOGY","suggestpathology");

        startActivity(intent);
    }
}
