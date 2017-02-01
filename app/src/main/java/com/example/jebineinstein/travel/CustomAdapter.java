package com.example.jebineinstein.travel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jebineinstein on 31/1/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<BusDataModel> dataSet=null;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView BusName;
        TextView Dtime;
        TextView Cost;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.BusName = (TextView) itemView.findViewById(R.id.busname);
            this.Dtime = (TextView) itemView.findViewById(R.id.depaturetime);
            this.Cost = (TextView) itemView.findViewById(R.id.buscost);
        }
    }

    public CustomAdapter(ArrayList<BusDataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        view.setOnClickListener(BusesList.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TextView textViewName = holder.BusName;
        TextView textItem = holder.Dtime;
        TextView textDescription = holder.Cost;

        textViewName.setText(dataSet.get(position).getBusname());
        textItem.setText(dataSet.get(position).getDpoint());
        textDescription.setText(dataSet.get(position).getCost());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
