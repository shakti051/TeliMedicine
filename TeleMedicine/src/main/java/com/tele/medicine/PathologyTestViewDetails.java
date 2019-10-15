package com.tele.medicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.tele.medicine.pathologyadapterview.PathologyTestViewAdapter;

/**
 * Created by Database on 9/1/2016.
 */
public class PathologyTestViewDetails extends AppCompatActivity {

    String patholagyName,pathologyLocation,callingActivity;
    TextView pathologyContactTV,pathologyLocationTV;
    RecyclerView mRecyclerView;
    PathologyTestViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathology_view_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            patholagyName = bundle.getString("PATHOLOGY_NAME");
            pathologyLocation = bundle.getString("PATHOLOGY_LOCATION");
            callingActivity = bundle.getString("FROM_PATHOLOGY");
            Log.e("plz_aja","plz_aja... "+callingActivity);

        }
        toolbar.setTitle(patholagyName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setUpIds();
        layoutManager = new LinearLayoutManager(PathologyTestViewDetails.this);
        mRecyclerView.setLayoutManager(layoutManager);
        setValues();
    }

    private void setUpIds() {
        pathologyLocationTV = (TextView)findViewById(R.id.pathAddressID);
        pathologyContactTV = (TextView)findViewById(R.id.pathContactID);
        mRecyclerView = (RecyclerView)findViewById(R.id.testListId);
    }

    private void setValues() {

        pathologyContactTV.setText("0120-4120912");
        pathologyLocationTV.setText(pathologyLocation);
        mAdapter = new PathologyTestViewAdapter(PathologyTestViewDetails.this,callingActivity);
        mRecyclerView.setAdapter(mAdapter);
    }
}
