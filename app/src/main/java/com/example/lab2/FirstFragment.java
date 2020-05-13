package com.example.lab2;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FirstFragment extends Fragment {
 View view;
 TextView posts;
 public Main2Activity activity2;
 PostDbAdapter helper;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState) {
  view = inflater.inflate(R.layout.fragment_first, container, false);
  activity2 = (Main2Activity)getActivity();
  posts = (TextView)view.findViewById(R.id.firstFragmentText);
  helper = new PostDbAdapter(activity2);
  String entries = helper.getCurrentPostData(activity2.currentUserName);
  posts.setText(entries);
  Toast.makeText(activity2.getApplicationContext(), entries, Toast.LENGTH_LONG).show();

 return view;
 }
}