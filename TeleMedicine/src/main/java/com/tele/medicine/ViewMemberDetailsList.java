package com.tele.medicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tele.medicine.adapterview.ViewMemberListAdapter;

/**
 * Created by Database on 9/24/2016.
 */
public class ViewMemberDetailsList extends AppCompatActivity {

    ViewMemberListAdapter viewMemberListAdapter;
    private RecyclerView memberList;
    LinearLayoutManager mLinearLayoutManager;
//    private RecyclerView.LayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_member_details_list);




        setUpIds();
        addAdapter();
    }

    private void addAdapter() {
        viewMemberListAdapter = new ViewMemberListAdapter(this);
        memberList.setAdapter(viewMemberListAdapter);

    }

    private void setUpIds() {

        memberList = (RecyclerView) findViewById(R.id.member_list_id);
        mLinearLayoutManager = new LinearLayoutManager(this);
        memberList.setLayoutManager(mLinearLayoutManager);
    }


}
