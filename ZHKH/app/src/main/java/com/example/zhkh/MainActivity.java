package com.example.zhkh;

import android.content.Intent;
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

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Schedule;
import com.example.zhkh.ApiInteractions.pojoes.Task;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.example.zhkh.ApiInteractions.pojoes.User;
import com.example.zhkh.Comunication.CommunicationFragment;
import com.example.zhkh.Fragments.ProfileFragment;
import com.example.zhkh.Schedule.ScheduleFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiWorker aw = new ApiWorker("http://85.143.11.233:8002/");
        IAuthApi api = aw.getLog();

        api.getSchedule(Singleton.getInstance().getToken()).enqueue(new Callback<List<Schedule>>()
        {

            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println("We got some troubles. But server is okay");
                    return;
                }

                Singleton.getInstance().setScheduleList(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }

        });
    }

}
