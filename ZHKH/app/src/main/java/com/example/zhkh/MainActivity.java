package com.example.zhkh;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
//
//    Retrofit retrofit =  new Retrofit.Builder()
//            .baseUrl("https://your.api.url/v2/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build();

    NavController navController;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_sсhedule:
                    navController.navigate(R.id.scheduleFragment);
                    return true;
                case R.id.navigation_comunication:
                    navController.navigate(R.id.fragmentCommunication);
                    return true;
                case R.id.navigation_profile:
                    navController.navigate(R.id.profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

}
