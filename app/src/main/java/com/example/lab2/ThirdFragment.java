package com.example.lab2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class ThirdFragment extends Fragment {
    View view;
    Button postButton;
    EditText postToAdd;
    public Main2Activity activity2;
    PostDbAdapter helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third, container, false);
        activity2 = (Main2Activity)getActivity();
        postToAdd = (EditText)view.findViewById(R.id.thirdFragmentInput);
        helper = new PostDbAdapter(activity2);
        postButton = (Button)view.findViewById(R.id.button2);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = activity2.currentUserName.toString();
                String text2 = postToAdd.getText().toString();
                if(text2.isEmpty() )
                {
                    Toast.makeText(activity2.getApplicationContext(), "Please enter text", Toast.LENGTH_LONG).show();
                }
                else
                {
                    long id = helper.insertPostData(text1, text2);
                    if(id<=0)
                    {
                        Toast.makeText(activity2.getApplicationContext(), "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(activity2.getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                        postToAdd.setText("");
                    }
                }
            }
        });
        return view;
    }
}
