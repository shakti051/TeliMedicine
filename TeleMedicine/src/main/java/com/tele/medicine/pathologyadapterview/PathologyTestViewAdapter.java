package com.tele.medicine.pathologyadapterview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tele.medicine.R;

/**
 * Created by Database on 9/1/2016.
 */
public class PathologyTestViewAdapter extends RecyclerView.Adapter<PathologyTestViewAdapter.ViewHolder> {

    private Context context;
    String callingActivity;
//    OnClickListener mClickListener;

    public PathologyTestViewAdapter(Context context,String callingActivity){
        this.context = context;
        this.callingActivity=callingActivity;
        Log.e("CallingActivity","CallingActivity... "+callingActivity);
//        mClickListener = (OnClickListener)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView testNameTv,preTestInfoTV,sampleColectionTV,reportTV,rupyeeTV,rupyeeStatusTV;
        public CardView cardView;
        public Button statusBtn,bookNowBtn;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            testNameTv = (TextView) view.findViewById(R.id.testNameID);
            preTestInfoTV = (TextView) view.findViewById(R.id.preTestInfoID);
            sampleColectionTV = (TextView) view.findViewById(R.id.sampleColectionID);
            reportTV = (TextView) view.findViewById(R.id.reportID);
            rupyeeTV = (TextView) view.findViewById(R.id.rupyeeID);
            statusBtn = (Button)view.findViewById(R.id.statusBtnID);
            bookNowBtn = (Button)view.findViewById(R.id.bookNowBtnID);

            if(callingActivity.equals("suggestpathology")){
                statusBtn.setVisibility(View.VISIBLE);
                statusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Referred Pathlaogy",Toast.LENGTH_LONG).show();
                    }
                });
            }else if(callingActivity.equals("allpathology")){
                bookNowBtn.setVisibility(View.VISIBLE);
                bookNowBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"All Pathlaogy",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_row_test_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.testNameTv.setText("TestName");
        holder.preTestInfoTV.setText("12 hours fasting is recommended");
        holder.sampleColectionTV.setText("Sample Daily by 11 AM");
        holder.reportTV.setText("Same day");
        holder.rupyeeTV.setText("5200/-");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

   /* public interface OnClickListener{

        public void onCardClick(int position, String pathologyName, String address);
        }*/


}
