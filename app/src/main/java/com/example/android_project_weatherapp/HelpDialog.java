package com.example.android_project_weatherapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class HelpDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        builder.setMessage(getString(R.string.HelpMain) + "\n\n" + getString(R.string.AddHelp) + "\n\n" + getString(R.string.DeleteHelp)).
                setNegativeButton(R.string.DismisHelp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setTitle(R.string.Information);
        return builder.create();
    }
}
