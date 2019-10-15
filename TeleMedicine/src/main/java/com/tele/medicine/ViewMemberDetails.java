package com.tele.medicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Database on 9/16/2016.
 */
public class ViewMemberDetails extends AppC {

    String relativeName[],relationship[];
    private TextView relativeNameTV,relationshipTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_member_details);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("View Member Details");
        setSupportActionBar(toolbar);

        Bundle bundle= getIntent().getExtras();
        relativeName=bundle.getString("RELATIVENAME");
        relationship = bundle.getString("RELATIONSHIP");
        Log.e("MEMBERNAME","membername..." +relativeName);
        Log.e("MEMBERRELATION","memberrelation..." +relationship);
        setUpIds();
        setUpValues();

    }

    private void setUpValues() {
        relativeNameTV.setText(relativeName);
        relationshipTV.setText(relationship);
    }


    private void setUpIds() {
        relativeNameTV = (TextView)findViewById(R.id.relativeNameID);
        relationshipTV = (TextView)findViewById(R.id.relationshipID);

    }
}
