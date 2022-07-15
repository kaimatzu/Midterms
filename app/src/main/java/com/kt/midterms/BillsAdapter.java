package com.kt.midterms;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BillsAdapter extends ArrayAdapter<Bill> {
    int resource;
    List<Bill> bills;

    public BillsAdapter(@NonNull Context context, int resource, @NonNull List<Bill> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.bills = objects;
    }

    @NonNull
    @Override
    // TODO Milestone B: Show History.
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout billView;
        Bill bill = getItem(position);
        if (convertView == null) {
            billView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, billView, true);
        } else {
            billView = (LinearLayout) convertView;
        }
        TextView tvMonthLbl = billView.findViewById(R.id.tvMonthLbl);
        TextView textcubic = billView.findViewById(R.id.textcubic);
        TextView tvPreviousLbl = billView.findViewById(R.id.tvPreviousLbl);
        TextView tvCurrentLbl = billView.findViewById(R.id.tvCurrentLbl);
        TextView tvPaymentLbl = billView.findViewById(R.id.tvPaymentLbl);
        TextView tvMonth = billView.findViewById(R.id.tvMonth);
        TextView tvConsumption = billView.findViewById(R.id.tvConsumption);
        TextView tvPrevious = billView.findViewById(R.id.tvPrevious);
        TextView tvCurrent = billView.findViewById(R.id.tvCurrent);
        TextView tvPayment = billView.findViewById(R.id.tvPayment);

        tvMonth.setText(String.valueOf(bill.month));
        tvConsumption.setText(String.valueOf((bill.current - bill.previous) * bill.type.diameter));
        tvPrevious.setText(String.valueOf(bill.previous));
        tvCurrent.setText(String.valueOf(bill.current));
        tvPayment.setText(String.valueOf(bill.get_bill()));

        if(bill.nightMode){
            tvMonthLbl.setTextColor(Color.WHITE);
            tvMonth.setTextColor(Color.WHITE);
            tvConsumption.setTextColor(Color.WHITE);
            textcubic.setTextColor(Color.WHITE);
            tvPreviousLbl.setTextColor(Color.WHITE);
            tvCurrentLbl.setTextColor(Color.WHITE);
            tvPaymentLbl.setTextColor(Color.WHITE);
            tvPrevious.setTextColor(Color.WHITE);
            tvCurrent.setTextColor(Color.WHITE);
            tvPayment.setTextColor(Color.WHITE);
        }

        return billView;
    }
}