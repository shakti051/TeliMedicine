package com.tele.medicine.adapterview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tele.medicine.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Database on 7/11/2016.
 */
public class TimeSlotRecyclerViewAdapter extends RecyclerView.Adapter<TimeSlotRecyclerViewAdapter.ViewHolder> {
    public Context mContext;
    ViewHolder viewHolder;
    OnTimeSlotClickListener mClickListener;
    ArrayList<HashMap<String, String>> aList = new ArrayList<>();
    HashMap<String, String> hashMap;

    String[] mornimgTimeSlot = {"Morning before 12 pm", "9:00 am  " ,"09:30 am  " ,"10:15 am", "10:30 am  ", "10:45 am  ", "11:00 am"};
    String[] afterNoonTimeSlot = {"After Noon 12 - 4 pm", "12:30 pm  ","01:15 pm  ","01:45 pm", "02:30 pm  ", "03:15 pm  ", "04:00 pm"};
    String[] eveningTimeSlot = {"Evening 4 - 8 pm", "05:15 pm  ","06:00 pm   ","06:30 pm", "07:00 pm  ", "07:30 pm  ", "08:15 pm"};


    public TimeSlotRecyclerViewAdapter(Context context, OnTimeSlotClickListener mClickListener) {
        mContext = context;
        this.mClickListener = mClickListener;

        hashMap = new HashMap<>();
        hashMap.put("HEADER", mornimgTimeSlot[0]);
        hashMap.put("TV1", mornimgTimeSlot[1]);
        hashMap.put("TV2", mornimgTimeSlot[2]);
        hashMap.put("TV3", mornimgTimeSlot[3]);
        hashMap.put("TV4", mornimgTimeSlot[4]);
        hashMap.put("TV5", mornimgTimeSlot[5]);
        hashMap.put("TV6", mornimgTimeSlot[6]);
        aList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("HEADER", afterNoonTimeSlot[0]);
        hashMap.put("TV1", afterNoonTimeSlot[1]);
        hashMap.put("TV2", afterNoonTimeSlot[2]);
        hashMap.put("TV3", afterNoonTimeSlot[3]);
        hashMap.put("TV4", afterNoonTimeSlot[4]);
        hashMap.put("TV5", afterNoonTimeSlot[5]);
        hashMap.put("TV6", afterNoonTimeSlot[6]);
        aList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("HEADER", eveningTimeSlot[0]);
        hashMap.put("TV1", eveningTimeSlot[1]);
        hashMap.put("TV2", eveningTimeSlot[2]);
        hashMap.put("TV3", eveningTimeSlot[3]);
        hashMap.put("TV4", eveningTimeSlot[4]);
        hashMap.put("TV5", eveningTimeSlot[5]);
        hashMap.put("TV6", eveningTimeSlot[6]);
        aList.add(hashMap);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView headerSlotTv, tv1, tv2, tv3, tv4, tv5, tv6;
        public ViewHolder(View view) {
            super(view);
            headerSlotTv = (TextView) view.findViewById(R.id.headerSlotTv);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            tv5 = (TextView) view.findViewById(R.id.tv5);
            tv6 = (TextView) view.findViewById(R.id.tv6);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_time_slot, null);
        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position )
    {
            holder.headerSlotTv.setText(aList.get(position).get("HEADER"));
            holder.tv1.setText(aList.get(position).get("TV1"));
            holder.tv2.setText(aList.get(position).get("TV2"));
            holder.tv3.setText(aList.get(position).get("TV3"));
            holder.tv4.setText(aList.get(position).get("TV4"));
            holder.tv5.setText(aList.get(position).get("TV5"));
            holder.tv6.setText(aList.get(position).get("TV6"));

            holder.tv4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v)
              {
                mClickListener.onClick(aList.get(position).get("TV4"));
              }
        });
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

    public interface OnTimeSlotClickListener
    {
        public void onClick(String tv4);
    }

}
