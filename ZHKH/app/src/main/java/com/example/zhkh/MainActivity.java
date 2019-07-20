package com.example.zhkh;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.zhkh.Comunication.CommunicationFragment;
import com.example.zhkh.Fragments.ProfileFragment;
import com.example.zhkh.Schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity{

    NavController navController;
    private FragmentTransaction ftrans;
    private BottomNavigationView.OnNavigationItemSelectedListener
            navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = new CommunicationFragment();
            ftrans = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_sсhedule:
                    selectedFragment = new ScheduleFragment();
                    break;
                case R.id.navigation_comunication:
                    selectedFragment = new CommunicationFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            ftrans.replace(R.id.fragment_conteiner, selectedFragment).commit();
            return true;
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_comunication);
    }

}
