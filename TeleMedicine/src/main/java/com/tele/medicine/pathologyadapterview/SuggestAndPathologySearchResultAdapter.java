package com.tele.medicine.pathologyadapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tele.medicine.R;

/**
 * Created by ASHA on 8/29/2016.
 */
public class SuggestAndPathologySearchResultAdapter extends RecyclerView.Adapter<SuggestAndPathologySearchResultAdapter.ViewHolder> {

    private Context mContext;
    OnClickListener mClickListener;


    String[] pathologyNameTv = {"Dr. Agarwal Pathology", "Religare Diagnostics", "Viva Pathology", "Lloyds Pathology", "Alpha Pathology", "Caring Pathology", "Appex Pathology", "Forties Helth Pathology"};
    String[] location = {"7,(Near srare bank ofindia)Ghaziabad", "Ground floor,A-24-A,Ghaziabad", "Ghaziabaad", "Ghaziabaad", "Noida", "Noida", "Noida", "Noida"};

    public SuggestAndPathologySearchResultAdapter(Context mContext){
        this.mContext = mContext;
        mClickListener = (OnClickListener)mContext;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pathologyNameTv, locationTv;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            pathologyNameTv = (TextView) view.findViewById(R.id.pharmacyNameTv);
            locationTv = (TextView) view.findViewById(R.id.locationTv);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_row_pathology_search_result,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        holder.pathologyNameTv.setText(pathologyNameTv[position]);
        holder.locationTv.setText(location[position]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onCardClick(holder.getLayoutPosition(), pathologyNameTv[position], location[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pathologyNameTv.length;
    }

    public interface OnClickListener
    {
        public void onCardClick(int position, String pathologyName, String address);
    }
}
