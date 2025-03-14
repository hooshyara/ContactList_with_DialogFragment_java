package com.example.contact_with_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {


    private MyDialogEventListener eventListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eventListener = (MyDialogEventListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mydialog, null, false);
        EditText editText = view.findViewById(R.id.et_dialog_input);
        builder.setView(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String fullName = bundle.getString("fullName");
            editText.setText(fullName); // نمایش نام مخاطب در EditText
        }

        Button okBTN = view.findViewById(R.id.btn_dialog_ok);
        okBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.length() > 0){
                    eventListener.onOkButtonClicked(editText.getText().toString());
                    dismiss();
                }
            }
        });

        Button cancelBTN = view.findViewById(R.id.btn_dialog_Cancel);
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onCancelButtonClicked();
                dismiss();
            }
        });
        return builder.create();
    }

    public  interface MyDialogEventListener{
        void onOkButtonClicked(String data);
        void onCancelButtonClicked();
    }
}
