package com.kt.midterms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

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

public class MainActivity extends AppCompatActivity {
    ArrayList<Pipe> pipeTypes;
    ArrayAdapter<Pipe> pipeAdapter;
    ArrayList<Bill> bills;
    int month;
    int pack;
    int last_consumption;

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

        ColorStateList white = ColorStateList.valueOf(Color.WHITE);
        ColorStateList gray = ColorStateList.valueOf(Color.GRAY);
        swNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    clMain.setBackgroundColor(Color.BLACK);
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
                } else {
                    clMain.setBackgroundColor(Color.WHITE);
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
                }
            }
        });
    }


    // TODO Milestone B: Show History.
    private void setHistoryAdapter() {

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
               int curr = Integer.parseInt(etCurr.getText().toString());;
               Pipe type = (Pipe) spPipe.getSelectedItem();

               Bill bill = new Bill(last_consumption, curr, type, pack, month);

               etResult.setText(String.valueOf(bill.get_bill()));
               month++;
               last_consumption = curr;
               etPrev.setText(String.valueOf(last_consumption));
               etCurr.setText("");
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
}