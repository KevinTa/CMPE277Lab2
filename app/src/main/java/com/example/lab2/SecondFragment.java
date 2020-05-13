package com.example.lab2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

public class SecondFragment extends Fragment {
    View view;
    public Main2Activity activity2;
    Button deleteButton;
    PostDbAdapter helper;
    EditText idEnter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);
        activity2 = (Main2Activity)getActivity();
        helper = new PostDbAdapter(activity2);
        idEnter = (EditText)view.findViewById(R.id.inputId);
        deleteButton = (Button)view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity2);
                alertDialogBuilder.setTitle("Confirm Deletion");
                alertDialogBuilder.setMessage("Are you sure you want to delete?");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String deleteId = idEnter.getText().toString();
                        helper.deletePost(deleteId);
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return view;
    }
}