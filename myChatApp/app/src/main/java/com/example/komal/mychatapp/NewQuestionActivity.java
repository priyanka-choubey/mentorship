package com.example.komal.mychatapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class NewQuestionActivity extends DialogFragment {

    String question;
    EditText etSearch;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        final View view=inflater.inflate(R.layout.activity_new_question,null);
//        final EditText et1 = (EditText) view.findViewById(R.id.asked);

        builder.setView(inflater.inflate(R.layout.activity_new_question, null))
                .setPositiveButton("Add Question", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                          //question=etSearch.getText().toString();
                        //Log.e("TAG","qbsjcgucgbuigx"+question);
//                        EditText edit=(EditText)builder.create().findViewById(R.id.dialog_edit);
//                        String text=edit.getText().toString();
//                        String s1=et1.getText().toString();
//                        Log.e("TAG","qbsjcgucgbuigx "+et1);
                      NewQuestionActivity.this.getDialog().cancel();


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewQuestionActivity.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

    public String getQuestion() {
        return question;
    }
}
