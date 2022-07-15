package com.kt.midterms;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

// TODO Milestone C: Add error dialog
public class BillDialogFragment extends DialogFragment {
    BillDialogListener listener;
    boolean noprev;
    public BillDialogFragment(boolean noprev){
        this.noprev = noprev;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BillDialogFragment.BillDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() +
                    " must implement DeleteNoteDialogListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        if(noprev){
            builder.setMessage(" ")
                    .setView(inflater.inflate(R.layout.bill_error_dialog_noprev, null))
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.onNoListenerMethod(BillDialogFragment.this);
                        }
                    });
        }
        else{
            builder.setMessage(" ")
                    .setView(inflater.inflate(R.layout.bill_error_dialog, null))
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.onYesListenerMethod(BillDialogFragment.this);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.onNoListenerMethod(BillDialogFragment.this);
                        }
                    });
        }

        return builder.create();
    }
    public interface BillDialogListener {
        public void onYesListenerMethod(DialogFragment dialog);
        public void onNoListenerMethod(DialogFragment dialog);
    }
}