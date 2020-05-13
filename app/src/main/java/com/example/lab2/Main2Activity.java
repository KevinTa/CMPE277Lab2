package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity {
    DrawerLayout d;
    String currentUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.openDrawer(Gravity.LEFT);
            }
        });
        setNavigationDrawer();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUserName = extras.getString("username");
        }
    }

    private void setNavigationDrawer() {
        d = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fr = null;
                int itemId = menuItem.getItemId();
                if (itemId == R.id.first) {
                    fr = new FirstFragment();
                } else if (itemId == R.id.second) {
                    fr = new SecondFragment();
                } else if (itemId == R.id.third) {
                    fr = new ThirdFragment();
                }

                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if (fr != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, fr);
                    transaction.commit();
                    d.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }


}
