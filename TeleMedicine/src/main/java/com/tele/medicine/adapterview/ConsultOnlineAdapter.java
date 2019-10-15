package com.tele.medicine.adapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.R;
import com.tele.medicine.utilities.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Database on 7/5/2016.
 */
public class ConsultOnlineAdapter extends RecyclerView.Adapter<ConsultOnlineAdapter.ViewHolder> {

    ArrayList<HashMap<String, String>> doctorListValue;
    private Context mContext;
    private String mScreenName;
    OnClickListener mClickListener;

    String[] doctorName = {"Dr. Subhash Tiwari", "Dr.Anil Sharma", "Dr. Aruna", "Dr. Niharika"};
    String[] doctorQualification = {"MBBS", "Diploma in Obstetrics", "MD-HRM", "MD, MBBS"};
    String[] doctorClinicName = {"Skin Aid Clinic", "LK Skin Center", "Health Point PolyClinic", "Dr. Jyotsna Clinic"};
    String[] clinicLocation = {"Ghaziabad", "Ghaziabad", "Ghaziabad", "Ghaziabad"};
    String[] feesAmount = {"300 INR", "400 INR", "100 INR", "200 INR"};

    public ConsultOnlineAdapter(Context mContext, OnClickListener mClickListener, String screenName, ArrayList<HashMap<String, String>> doctorListValue) {
        this.mContext = mContext;
        this.doctorListValue = doctorListValue;
        this.mScreenName = screenName;
        this.mClickListener = mClickListener;
    }

    public ConsultOnlineAdapter(Context mContext, OnClickListener mClickListener, String screenName) {
        this.mContext = mContext;
        this.mScreenName = screenName;
        this.mClickListener = mClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorNameTv, feesAmountTv, qualificationTv, clinicNameTv, clinicLocationTv;
        public CircularImageView profileImg, overflow;
        public Button onlineConsultBtn, clinicAppointmentBtn;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            doctorNameTv = (TextView) view.findViewById(R.id.doctorNameTv);
            feesAmountTv = (TextView) view.findViewById(R.id.feesTv);
            profileImg = (CircularImageView) view.findViewById(R.id.circularImgViewTv);
            qualificationTv = (TextView) view.findViewById(R.id.qualificationTv);
            clinicNameTv = (TextView) view.findViewById(R.id.clinicNameTv);
            clinicLocationTv = (TextView) view.findViewById(R.id.clinicLocationTv);
            onlineConsultBtn = (Button) view.findViewById(R.id.onlineConsultBtn);
            clinicAppointmentBtn = (Button) view.findViewById(R.id.appointmentsBtn);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.single_row_consult_online, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (mScreenName.equals("CONSULT_ONLINE")) {
            holder.profileImg.setImageResource(R.drawable.dentist_bg);
            holder.doctorNameTv.setText(doctorName[position]);
            holder.qualificationTv.setText(doctorQualification[position]);
            holder.clinicNameTv.setText(doctorClinicName[position]);
            holder.clinicLocationTv.setText(clinicLocation[position]);
            holder.feesAmountTv.setText(feesAmount[position]);
            holder.onlineConsultBtn.setVisibility(View.VISIBLE);

            holder.onlineConsultBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(holder.getLayoutPosition(), doctorName[position], doctorClinicName[position], clinicLocation[position]);
                }
            });

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mClickListener.onCardClick(doctorName[position], doctorClinicName[position], clinicLocation[position]);
                }
            });

        } else if (mScreenName.equals("CLINIC_APPOINTMENTS")) {
            holder.profileImg.setImageResource(R.drawable.dentist_bg);
            holder.doctorNameTv.setText(doctorListValue.get(position).get("NAME"));
            holder.qualificationTv.setText(doctorListValue.get(position).get("QUALIFICATION"));
            holder.clinicNameTv.setText(doctorListValue.get(position).get("HOSPITAL_NAME"));
            holder.clinicLocationTv.setText(doctorListValue.get(position).get("ADDRESS"));
            holder.feesAmountTv.setText(doctorListValue.get(position).get("CONSULTATION_FEE"));
            holder.clinicAppointmentBtn.setVisibility(View.VISIBLE);

            holder.clinicAppointmentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(holder.getLayoutPosition(), doctorListValue.get(position).get("NAME"), doctorListValue.get(position).get("HOSPITAL_NAME"), doctorListValue.get(position).get("ADDRESS"));
                }
            });
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mClickListener.onCardClick(doctorName[position], doctorClinicName[position], clinicLocation[position]);
                    mClickListener.onCardClick(doctorListValue.get(position).get("NAME"), doctorListValue.get(position).get("HOSPITAL_NAME"),     //this data move in move in ClinicAppointmentsTabFragment class using interface below
                            doctorListValue.get(position).get("ADDRESS"), doctorListValue.get(position).get("CONSULTATION_FEE"), doctorListValue.get(position).get("APPOINTMENT_SLOT_TIME"));
                }
            });
        }
    }

    public interface OnClickListener {
        public void onClick(int position, String doctorName, String clinicName, String clinicLocation);
//        public void onCardClick(String doctorName, String clinicName, String clinicLocation);

        public void onCardClick(String name, String hospital_name, String address, String consultation_fee, String appointment_slot_time); // to
    }

    @Override
    public int getItemCount() {
        if (mScreenName.equals("CLINIC_APPOINTMENTS")) {
            return doctorListValue.size();
        }
        return doctorName.length;
    }
}
