package com.example.heraldchat;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Afterlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        BottomNavigationView bottonNav = findViewById(R.id.nav_buttom);
        bottonNav.setOnNavigationItemSelectedListener(navListner);

        //to show home at first
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Chat()).commit();

    }

    BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


            Fragment selectedFrag = null;

                    switch (menuItem.getItemId()) {

                        case R.id.chat:
                            selectedFrag = new Chat();
                            break;

                        case R.id.people:
                            selectedFrag = new Friends();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFrag).commit();
                    return true;
                }
            };

    }

