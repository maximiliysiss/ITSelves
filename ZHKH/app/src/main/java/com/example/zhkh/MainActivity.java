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

import com.example.zhkh.ApiInteractions.ApiWorker;
import com.example.zhkh.ApiInteractions.IAuthApi;
import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Key;
import com.example.zhkh.ApiInteractions.pojoes.Token;
import com.example.zhkh.Comunication.CommunicationFragment;
import com.example.zhkh.Fragments.ProfileFragment;
import com.example.zhkh.Schedule.ScheduleFragment;

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


        ApiWorker aw = new ApiWorker("http://85.143.10.92:8001/");
        IAuthApi login = aw.getLog();

        Key key = new Key();
        key.setKey("5858-1678-c864-807b");

        login.getAuth(key).enqueue(new Callback<Token>()
        {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response)
            {
                if(!response.isSuccessful())
                {
                    System.out.println("We got some troubles. But server is okay");
                    return;
                }
                tok = response.body().getToken();
                Singleton.getInstance().setToken(tok);
                System.out.println("Success! "+tok);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t)
            {
                System.out.println(t.getMessage());
            }

        });



        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.navigation_comunication);
    }

}
