package com.example.zhkh;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;

import androidx.navigation.NavController;

import com.example.zhkh.Comunication.CommunicationFragment;
import com.example.zhkh.Fragments.ProfileFragment;
import com.example.zhkh.Schedule.ScheduleFragment;

public class MainActivity extends AppCompatActivity{
    private String tok;
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
