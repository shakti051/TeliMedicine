package com.tele.medicine.doctoradapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tele.medicine.R;
import com.tele.medicine.doctordashboard.FixedAppointment;
import com.tele.medicine.utilities.CircularImageView;

/**
 * Created by Database on 7/15/2016.
 */
public class DoctorAppointmentFixedAdapter extends RecyclerView.Adapter<DoctorAppointmentFixedAdapter.ViewHolder> {

    private Context mContext;
    OnClickListener mClickListener;
    private String screenName;

    String[] patientName = {"Aman Verma", "Anil Sharma", "Ashwini Kumar", "Niharika Singh"};
    String[] dateTime = {"10-07-16/07:30 pm", "18-07-16/10:30 am", "20-07-16/11:30 am", "21-07-16 /02:30 pm"};
    String[] age = {"25", "28", "32", "26"};
    String[] location = {"New Delhi", "Mumbai", "Ghaziabaad", "Chandigarh"};

    /*public ConsultOnlineAdapter(Context mContext, OnClickListener mClickListener, String screenName) {
        this.mContext = mContext;
        this.mScreenName = screenName;
        this.mClickListener = mClickListener;
    }*/

    public DoctorAppointmentFixedAdapter(Context mContext, OnClickListener mClickListener) {
        this.mContext = mContext;
        this.mClickListener = mClickListener;
    }

    public DoctorAppointmentFixedAdapter(Context mContext)
    {
        this.mContext = mContext;
        mClickListener = (OnClickListener)mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView patientNameTv, ageTv, dateTimeTv, addressTv;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            patientNameTv = (TextView) view.findViewById(R.id.patientNameTv);
            ageTv = (TextView) view.findViewById(R.id.ageTv);
            dateTimeTv = (TextView) view.findViewById(R.id.dateTimeTv);
            addressTv = (TextView) view.findViewById(R.id.addressTv);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.single_row_upcoming_appointment, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

               holder.patientNameTv.setText(patientName[position]);
               holder.ageTv.setText(age[position]);
               holder.dateTimeTv.setText(dateTime[position]);
               holder.addressTv.setText(location[position]);

               holder.cardView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mClickListener.onCardClick(patientName[position], age[position], dateTime[position], location[position]);
                   }
               });
        }

    @Override
    public int getItemCount() {
        return patientName.length;
    }

    public interface OnClickListener
    {
        public void onCardClick(String patientName, String age, String dateTime, String address);
    }

}




