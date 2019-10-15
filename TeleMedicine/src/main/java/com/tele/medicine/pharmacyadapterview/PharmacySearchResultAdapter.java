package com.tele.medicine.pharmacyadapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tele.medicine.R;

/**
 * Created by Database on 7/29/2016.
 */
public class PharmacySearchResultAdapter extends RecyclerView.Adapter<PharmacySearchResultAdapter.ViewHolder> {

    private Context mContext;
    OnClickListener mClickListener;

    String[] pharmacyName = {"Apollo Pharmacy", "Thulasi Pharmacy", "Viva Pharmacy", "Lloyds Pharmacy", "Alpha Pharmacy", "Caring Pharmacy", "Appex Pharmacy", "Forties Helth Pharmacy"};
    String[] location = {"Ghaziabad", "Ghaziabad", "Ghaziabaad", "Ghaziabaad", "Noida", "Noida", "Noida", "Noida"};



    /*public ConsultOnlineAdapter(Context mContext, OnClickListener mClickListener, String screenName) {
        this.mContext = mContext;
        this.mScreenName = screenName;
        this.mClickListener = mClickListener;
    }*/

    public PharmacySearchResultAdapter(Context mContext) {
        this.mContext = mContext;
        mClickListener = (OnClickListener)mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pharmacyNameTv, locationTv;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            pharmacyNameTv = (TextView) view.findViewById(R.id.pharmacyNameTv);
            locationTv = (TextView) view.findViewById(R.id.locationTv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.single_row_pharmacy_search_result, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.pharmacyNameTv.setText(pharmacyName[position]);
        holder.locationTv.setText(location[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                    /*cardview click that send data to below cardlistener*/
                mClickListener.onCardClick(holder.getLayoutPosition(), pharmacyName[position], location[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pharmacyName.length;
    }

    public interface OnClickListener
    {
        public void onCardClick(int position, String pharmacyName, String address); /*this send data to pharmactsearchResult class*/
    }

}
