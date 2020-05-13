package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText Name;
    EditText Password;
    UserDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Name= (EditText) findViewById(R.id.inputId);
        Password= (EditText) findViewById(R.id.inputPassword);
        helper = new UserDbAdapter(this);
    }
    public void Register(View view)
    {
        String nameText = Name.getText().toString();
        String passwordText = Password.getText().toString();
        if(nameText.isEmpty() || passwordText.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter Both Name and Password", Toast.LENGTH_LONG).show();
        }
        else
        {
            long id = helper.insertUserData(nameText,passwordText);
            if(id<=0)
            {
                Toast.makeText(getApplicationContext(), "Insertion Unsuccessful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Password.setText("");
            } else
            {
                Toast.makeText(getApplicationContext(), "Insertion Successful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Password.setText("");
            }
        }
    }


}
