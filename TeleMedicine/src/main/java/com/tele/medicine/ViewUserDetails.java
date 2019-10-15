package com.tele.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by Database on 9/16/2016.
 */
public class ViewUserDetails extends AppCompatActivity implements View.OnClickListener{

    private Button viewFamilyDetailsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details_layout);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("View Personal Details");
        setSupportActionBar(toolbar);

        setUpIds();
        setClickListenerMethod();


    }

    private void setClickListenerMethod() {
        viewFamilyDetailsBtn.setOnClickListener(this);
    }

    private void setUpIds() {

        viewFamilyDetailsBtn = (Button)findViewById(R.id.viewFamilyDetailsID);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.viewFamilyDetailsID:
            {
                Intent intent = new Intent(this,ViewMemberDetailsList.class);
                startActivity(intent);
            }
        }
    }
}
