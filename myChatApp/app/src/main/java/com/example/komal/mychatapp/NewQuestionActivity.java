package com.example.komal.mychatapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class NewQuestionActivity extends DialogFragment {

    String question;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.activity_new_question, null);
        final EditText etSearch = (EditText)mView.findViewById(R.id.add_ques);

        builder.setView(inflater.inflate(R.layout.activity_new_question, null))
                .setPositiveButton("Add Question", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                          // question=etSearch.getText().toString();
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
