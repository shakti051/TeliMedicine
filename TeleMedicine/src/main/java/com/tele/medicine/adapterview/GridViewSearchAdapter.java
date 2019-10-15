package com.tele.medicine.adapterview;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Database on 6/30/2016.
 */
public class GridViewSearchAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<HashMap<String, String>> specialitylistValue;
    private static LayoutInflater inflater=null;
    Holder holder;
    public Integer[] mImgBgIds = {
            R.drawable.dermatologist_bg, R.drawable.dentist_bg, R.drawable.general_physician_bg, R.drawable.gynaecologist_bg,
            R.drawable.homeopathy_bg, R.drawable.psychologist_bg, R.drawable.psychiatrist_bg, R.drawable.dermatologist_bg,
            R.drawable.dermatologist_bg, R.drawable.dentist_bg, R.drawable.general_physician_bg, R.drawable.gynaecologist_bg,
            R.drawable.homeopathy_bg, R.drawable.psychologist_bg, R.drawable.psychiatrist_bg, R.drawable.dermatologist_bg,
            R.drawable.dermatologist_bg, R.drawable.dentist_bg, R.drawable.general_physician_bg, R.drawable.gynaecologist_bg,
            R.drawable.homeopathy_bg
    };
    public String[] specialityName = {
            "Dermatologist", "Dentist",
            "General Physician", "Gynaecologist",
            "Homeopathy", "Psychologist",
            "Psychiatrist", "Ayurveda",
    };

    public GridViewSearchAdapter(Context c, ArrayList<HashMap<String, String>> specialitylistValue) {
        mContext = c;
        this.specialitylistValue = specialitylistValue;
        inflater = ( LayoutInflater )mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.single_grid_view, null);
        holder.tv=(TextView) rowView.findViewById(R.id.gridTextViewID);
        holder.img=(ImageView) rowView.findViewById(R.id.gridImageViewID);

        holder.tv.setText(specialitylistValue.get(position).get("SPECIALITY"));
        holder.img.setImageResource(mImgBgIds[position]);
        return rowView;
    }

    public int getCount() {
        return specialitylistValue.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
