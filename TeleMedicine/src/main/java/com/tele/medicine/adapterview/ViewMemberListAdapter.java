package com.tele.medicine.adapterview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tele.medicine.R;
import com.tele.medicine.ViewMemberDetails;
import com.tele.medicine.ViewMemberDetailsList;

/**
 * Created by Database on 9/24/2016.
 */
public class ViewMemberListAdapter extends RecyclerView.Adapter<ViewMemberListAdapter.ViewHolder> {


    ViewHolder viewHolder;
    String relName[]={"Atul","Naveen","Praveen","Amit","Kabir","Rahul","Ashu","Mayank"};
    String relRelation[]={"Brother","Friend","Brother","Self","Sir","Senior","TeamLeader"};
    private Context context;

    public ViewMemberListAdapter(ViewMemberDetailsList viewMemberDetailsList) {
        this.context=viewMemberDetailsList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_member_single_list_item, parent,false);
        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewMemberListAdapter.ViewHolder holder, int position) {

        holder.nameTV.setText(relName[position]);
        holder.relationshipTV.setText(relRelation[position]);
        holder.memberLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewMemberDetails.class);
                intent.putExtra("RELATIVENAME",relName);
                intent.putExtra("RELATIONSHIP",relRelation);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTV,relationshipTV;
        public LinearLayout memberLayout;
        public ViewHolder(View view) {
            super(view);
            nameTV = (TextView)view.findViewById(R.id.nameTxtID);
            relationshipTV = (TextView)view.findViewById(R.id.relationTxtID);
            memberLayout = (LinearLayout)view.findViewById(R.id.cardView_Layout);
        }
    }
}
