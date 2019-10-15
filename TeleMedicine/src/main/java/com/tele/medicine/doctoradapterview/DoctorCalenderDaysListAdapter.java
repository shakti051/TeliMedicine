package com.tele.medicine.doctoradapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tele.medicine.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Database on 7/18/2016.
 */
public class DoctorCalenderDaysListAdapter extends RecyclerView.Adapter<DoctorCalenderDaysListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mDates;
   // OnClickListener mClickListener;

    public DoctorCalenderDaysListAdapter(Context mContext, ArrayList<String> dates) {
        this.mContext = mContext;
        this.mDates = dates;
       // this.mClickListener = mClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView daysListTv;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            daysListTv = (TextView) view.findViewById(R.id.daysListTv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.single_row_cal_days_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.daysListTv.setText(mDates.get(position));
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onCardClick(patientName[position], age[position], dateTime[position], location[position]);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

  /*  public interface OnClickListener
    {
        public void onCardClick(String patientName, String age, String dateTime, String address);
    } */
}
