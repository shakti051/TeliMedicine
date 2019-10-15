package com.tele.medicine.adapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tele.medicine.AppointmentBookedViewDetails;
import com.tele.medicine.R;

import java.util.ArrayList;

/**
 * Created by Database on 7/20/2016.
 */
public class BookedAppointmentListAdapter extends RecyclerView.Adapter<BookedAppointmentListAdapter.ViewHolder> {

    private Context mContext;
    private String doctorName,clinicName, appointmentDate, appointmentTime;
    private ArrayList<String> bookList;
    // OnClickListener mClickListener;

    public BookedAppointmentListAdapter(Context mContext, String doctorName, String clinicName, String appointmentDate, String appointmentTime)
    {
        this.mContext = mContext;
        this.doctorName = doctorName;
        this.clinicName = clinicName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        // this.mClickListener = mClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorNameTv, clinicNameTv, bookTmeSlotTv, patientNameTv, dateSlotTv;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            doctorNameTv = (TextView) view.findViewById(R.id.doctorNameTv);
            clinicNameTv = (TextView) view.findViewById(R.id.ClinicNameTv);
            bookTmeSlotTv = (TextView) view.findViewById(R.id.timeSlotTv);
            patientNameTv = (TextView) view.findViewById(R.id.patientNameTv);
            dateSlotTv = (TextView) view.findViewById(R.id.dateSlotTv);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.single_row_appointment_booked, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.doctorNameTv.setText(doctorName);
        holder.clinicNameTv.setText(clinicName);
        holder.bookTmeSlotTv.setText(appointmentTime);
        holder.patientNameTv.setText("Vishal");
        holder.dateSlotTv.setText(appointmentDate);
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onCardClick(patientName[position], age[position], dateTime[position], location[position]);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    /*  public interface OnClickListener
      {
          public void onCardClick(String patientName, String age, String dateTime, String address);
      } */
}
