package com.example.lab2;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dLayout;
    EditText userName;
    EditText userPassword;
    UserDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new UserDbAdapter(this);
        userName = (EditText)findViewById(R.id.inputId);
        userPassword = (EditText)findViewById(R.id.inputPassword);
    }
    public void LogIn(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        String nameText = userName.getText().toString();
        String passwordText = userPassword.getText().toString();
        if(button_text.equals("Log In"))
        {
            if(nameText.isEmpty() || passwordText.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Enter Both Name and Password", Toast.LENGTH_LONG).show();
            }
            else
            {
                String receivedData = helper.getCurrentUserData(nameText, passwordText);
                if(receivedData.trim() == "")
                {
                    Toast.makeText(getApplicationContext(), "Incorrect user name or password or not registered", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Received Data : " + receivedData, Toast.LENGTH_LONG).show();
                    Intent login = new Intent(this,Main2Activity.class);
                    login.putExtra("username", nameText);
                    startActivity(login);
                }
            }
        }
        else if (button_text.equals("Sign Up"))
        {
            String value="Activity3 data received";
            Intent signup = new Intent(this,Main3Activity.class);
            startActivity(signup);

        }
    }
}

