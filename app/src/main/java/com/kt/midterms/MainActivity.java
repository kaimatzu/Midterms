package com.kt.midterms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BillDialogFragment.BillDialogListener{
    ArrayList<Pipe> pipeTypes;
    ArrayAdapter<Pipe> pipeAdapter;
    ArrayList<Bill> bills;
    BillsAdapter billsAdapter;
    FragmentManager fm;
    int month;
    int pack;
    int last_consumption;
    int last_difference;
    boolean nightMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bills = new ArrayList<>();
        month = 1;
        last_consumption = 0;
        setPipeAdapter();
        btnCalculateListenerMethod();
        setHistoryAdapter();
        nightModeListenerMethod();
        radioGroupCheckOnChangedListener();

        fm = getSupportFragmentManager();
    }

    // TODO Milestone A: Use Day-Night mode.
    private void nightModeListenerMethod() {
        ConstraintLayout clMain = findViewById(R.id.clMain);
        Switch swNight = findViewById(R.id.swNight);
        TextView tvTitle = findViewById(R.id.tvTitle);

        TextView tvLblPrev = findViewById(R.id.tvLblPrev);
        EditText etPrev = findViewById(R.id.etPrev);

        TextView tvLblNew = findViewById(R.id.tvLblNew);
        EditText etNew = findViewById(R.id.etNew);

        TextView tvLblPipe = findViewById(R.id.tvLblPipe);
        Spinner spPipe = findViewById(R.id.spPipe);

        TextView tvLblPackage = findViewById(R.id.tvLblPackage);
        RadioButton rbPremium = findViewById(R.id.rbPremium);
        RadioButton rbRegular = findViewById(R.id.rbRegular);
        RadioButton rbBasic = findViewById(R.id.rbBasic);

        TextView tvLblBill = findViewById(R.id.tvLblBill);
        EditText etResult = findViewById(R.id.etResult);

        TextView tvLblHistory = findViewById(R.id.tvLblHistory);
        ListView lvHistory = findViewById(R.id.lvHistory);


        ColorStateList white = ColorStateList.valueOf(Color.WHITE);
        ColorStateList gray = ColorStateList.valueOf(Color.GRAY);
        swNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                nightMode = isChecked;
                if (isChecked) {
                    clMain.setBackgroundColor(Color.BLACK);
                    swNight.setTextColor(Color.WHITE);
                    tvTitle.setTextColor(Color.WHITE);

                    tvLblPrev.setTextColor(Color.WHITE);
                    etPrev.setTextColor(Color.WHITE);
                    etPrev.setBackgroundTintList(white);

                    tvLblNew.setTextColor(Color.WHITE);
                    etNew.setTextColor(Color.WHITE);
                    etNew.setBackgroundTintList(white);
                    etNew.setHintTextColor(Color.WHITE);

                    tvLblPipe.setTextColor(Color.WHITE);
                    View v = spPipe.getSelectedView();
                    ((TextView)v).setTextColor(Color.WHITE);

                    tvLblPackage.setTextColor(Color.WHITE);
                    rbPremium.setTextColor(Color.WHITE);
                    rbRegular.setTextColor(Color.WHITE);
                    rbBasic.setTextColor(Color.WHITE);

                    tvLblBill.setTextColor(Color.WHITE);
                    etResult.setTextColor(Color.WHITE);
                    etResult.setBackgroundTintList(white);

                    tvLblHistory.setTextColor(Color.WHITE);
                    lvHistory.setDivider(new ColorDrawable(getResources().getColor(R.color.white, getTheme())));
                    lvHistory.setDividerHeight(2);
                    for(int i = 0; i < lvHistory.getChildCount(); i++){
                        View lvView = lvHistory.getChildAt(i);
                        TextView tvMonthLbl = lvView.findViewById(R.id.tvMonthLbl);
                        TextView tvMonth = lvView.findViewById(R.id.tvMonth);
                        TextView tvConsumption = lvView.findViewById(R.id.tvConsumption);
                        TextView textcubic = lvView.findViewById(R.id.textcubic);
                        TextView tvPreviousLbl = lvView.findViewById(R.id.tvPreviousLbl);
                        TextView tvCurrentLbl = lvView.findViewById(R.id.tvCurrentLbl);
                        TextView tvPaymentLbl = lvView.findViewById(R.id.tvPaymentLbl);
                        TextView tvPrevious = lvView.findViewById(R.id.tvPrevious);
                        TextView tvCurrent = lvView.findViewById(R.id.tvCurrent);
                        TextView tvPayment = lvView.findViewById(R.id.tvPayment);

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
                } else {
                    clMain.setBackgroundColor(Color.WHITE);
                    swNight.setTextColor(Color.GRAY);
                    tvTitle.setTextColor(Color.GRAY);

                    tvLblPrev.setTextColor(Color.GRAY);
                    etPrev.setTextColor(Color.BLACK);
                    etPrev.setBackgroundTintList(gray);

                    tvLblNew.setTextColor(Color.GRAY);
                    etNew.setTextColor(Color.BLACK);
                    etNew.setBackgroundTintList(gray);
                    etNew.setHintTextColor(Color.GRAY);

                    tvLblPipe.setTextColor(Color.GRAY);
                    View v = spPipe.getSelectedView();
                    ((TextView)v).setTextColor(Color.BLACK);

                    tvLblPackage.setTextColor(Color.GRAY);
                    rbPremium.setTextColor(Color.BLACK);
                    rbRegular.setTextColor(Color.BLACK);
                    rbBasic.setTextColor(Color.BLACK);

                    tvLblBill.setTextColor(Color.GRAY);
                    etResult.setTextColor(Color.BLACK);
                    etResult.setBackgroundTintList(gray);

                    tvLblHistory.setTextColor(Color.GRAY);
                    lvHistory.setDivider(new ColorDrawable(getResources().getColor(R.color.gray, getTheme())));
                    lvHistory.setDividerHeight(2);
                    for(int i = 0; i < lvHistory.getChildCount(); i++){
                        View lvView = lvHistory.getChildAt(i);

                        TextView tvMonthLbl = lvView.findViewById(R.id.tvMonthLbl);
                        TextView tvMonth = lvView.findViewById(R.id.tvMonth);
                        TextView tvConsumption = lvView.findViewById(R.id.tvConsumption);
                        TextView textcubic = lvView.findViewById(R.id.textcubic);
                        TextView tvPreviousLbl = lvView.findViewById(R.id.tvPreviousLbl);
                        TextView tvCurrentLbl = lvView.findViewById(R.id.tvCurrentLbl);
                        TextView tvPaymentLbl = lvView.findViewById(R.id.tvPaymentLbl);
                        TextView tvPrevious = lvView.findViewById(R.id.tvPrevious);
                        TextView tvCurrent = lvView.findViewById(R.id.tvCurrent);
                        TextView tvPayment = lvView.findViewById(R.id.tvPayment);

                        tvMonthLbl.setTextColor(Color.GRAY);
                        tvMonth.setTextColor(Color.GRAY);
                        tvConsumption.setTextColor(Color.GRAY);
                        textcubic.setTextColor(Color.GRAY);
                        tvPreviousLbl.setTextColor(Color.GRAY);
                        tvCurrentLbl.setTextColor(Color.GRAY);
                        tvPaymentLbl.setTextColor(Color.GRAY);
                        tvPrevious.setTextColor(Color.GRAY);
                        tvCurrent.setTextColor(Color.GRAY);
                        tvPayment.setTextColor(Color.GRAY);
                    }
                }
            }
        });
    }


    // TODO Milestone B: Show History.
    private void setHistoryAdapter() {
        ListView lvHistory = findViewById(R.id.lvHistory);
        billsAdapter = new BillsAdapter(getBaseContext(), R.layout.bills_layout, bills);
        lvHistory.setAdapter(billsAdapter);
    }

    // // TODO Milestone 3: Calculate bill.
    private void btnCalculateListenerMethod() {
        Button calculateButton = findViewById(R.id.btnCalculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               EditText etPrev = findViewById(R.id.etPrev);
               EditText etCurr = findViewById(R.id.etNew);
               EditText etResult = findViewById(R.id.etResult);
               Spinner spPipe = findViewById(R.id.spPipe);
               last_consumption = Integer.parseInt(etPrev.getText().toString());

               String currString = etCurr.getText().toString();
               int curr;
               if(!currString.matches("^[0-9]+$")){
                   BillDialogFragment dialog = new BillDialogFragment(month == 1);
                   dialog.show(fm, "InvalidInput");
               }
               else if(Integer.parseInt(currString) < last_consumption){
                   BillDialogFragment dialog = new BillDialogFragment(month == 1);
                   dialog.show(fm, "InvalidInput");
               }
               else{
                   curr = Integer.parseInt(currString);
                   Pipe type = (Pipe) spPipe.getSelectedItem();
                   Bill bill = new Bill(last_consumption, curr, type, pack, month, nightMode);
                   etResult.setText(String.valueOf(bill.get_bill()));
                   month++;
                   last_difference = curr - last_consumption;
                   last_consumption = curr;
                   etPrev.setText(String.valueOf(last_consumption));
                   etCurr.setText("");
                   bills.add(bill);
                   billsAdapter.notifyDataSetChanged();
               }

           }
       });
    }

    private void radioGroupCheckOnChangedListener(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbPremium:
                        pack = 0;
                        break;
                    case R.id.rbRegular:
                        pack = 1;
                        break;
                    case R.id.rbBasic:
                        pack = 2;
                        break;
                }
            }
        });
    }

    /**
     * The pipe list is already initialized. There is no need to revise this.
     */
    private void setPipeAdapter() {
        pipeTypes = new ArrayList<>();
        pipeTypes.add(new Pipe("Arad", 0.5));
        pipeTypes.add(new Pipe("Arad", 0.7));
        pipeTypes.add(new Pipe("Arad", 0.2));
        pipeTypes.add(new Pipe("Ace", 0.5));
        pipeTypes.add(new Pipe("Ace", 0.2));
        Spinner spPipe = findViewById(R.id. spPipe);
        pipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pipeTypes);
        spPipe.setAdapter(pipeAdapter);
    }

    @Override
    public void onYesListenerMethod(DialogFragment dialog) {
        EditText etPrev = findViewById(R.id.etPrev);
        EditText etCurr = findViewById(R.id.etNew);
        EditText etResult = findViewById(R.id.etResult);
        Spinner spPipe = findViewById(R.id.spPipe);
        last_consumption = Integer.parseInt(etPrev.getText().toString());

        int curr = last_difference + last_consumption;

        Pipe type = (Pipe) spPipe.getSelectedItem();
        Bill bill = new Bill(last_consumption, curr, type, pack, month, nightMode);
        etResult.setText(String.valueOf(bill.get_bill()));
        month++;
        last_consumption = curr;
        etPrev.setText(String.valueOf(last_consumption));
        etCurr.setText("");
        bills.add(bill);
        billsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoListenerMethod(DialogFragment dialog) {

    }
}