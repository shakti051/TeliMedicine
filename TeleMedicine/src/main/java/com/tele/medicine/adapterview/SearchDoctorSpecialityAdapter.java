package com.tele.medicine.adapterview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.R;
import com.tele.medicine.SearchResultActivity;
import com.tele.medicine.utilities.PharmacyContent;
import com.tele.medicine.utilities.ProjectConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Database on 7/4/2016.
 */
public class SearchDoctorSpecialityAdapter extends RecyclerView.Adapter<SearchDoctorSpecialityAdapter.ViewHolder> {
    public Context mContext;
    static OnSearchClickListener mClickListener;
    ArrayList<HashMap<String, String>> listValue;
    ViewHolder viewHolder;
    private String screenName;
    List<String> searchItem;

    public SearchDoctorSpecialityAdapter(List<String> searchItem, Context context, String screenName) {
        mContext = context;
        this.screenName = screenName;
        this.searchItem = searchItem;
        mClickListener = (OnSearchClickListener)mContext;
    }

    public SearchDoctorSpecialityAdapter(ArrayList<HashMap<String, String>> listValue, Context context, String doctor_search_specialty)
    {
        mContext = context;
        this.listValue = listValue;
        this.screenName = doctor_search_specialty;
        mClickListener = (OnSearchClickListener)mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.searchDoctorSpeciality);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_serach_doctor_speciality, null);

        viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position ) {

        if (screenName.equals("LOCATION_SEARCH"))
        {
            holder.name.setText(searchItem.get(position));
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onSearchClick(searchItem.get(position));

                    ProjectConstant.isLocationSelected=true;

                }
            });

        }else if (screenName.equals("DOCTOR_SEARCH_SPECIALTY"))
        {
            holder.name.setText(listValue.get(position).get("SPECIALITY"));
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onSearchClick(listValue.get(position).get("SPECIALITY"));
//                Toast.makeText(mContext, "This is position " + position, Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    public interface OnSearchClickListener
    {
        public void onSearchClick(String speciality);
    }

    @Override
    public int getItemCount() {
        if(screenName.equals("LOCATION_SEARCH")) {
           return searchItem.size();
        }
        return listValue.size();
    }
}
