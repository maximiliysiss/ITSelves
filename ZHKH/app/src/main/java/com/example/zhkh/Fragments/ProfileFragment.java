package com.example.zhkh.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhkh.ApiInteractions.Singleton;
import com.example.zhkh.ApiInteractions.pojoes.Address;
import com.example.zhkh.R;
import com.example.zhkh.Schedule.ScheduleFragment;


public class ProfileFragment extends Fragment {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "key";
    private SharedPreferences mSettings;
    TextView fio, adress, ukInfo, personalKey;
    ImageView userPhoto;
    ImageButton changeKey;
    Button addDataCounter;
    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        fio = (TextView) view.findViewById(R.id.fioTextView);
        adress = (TextView) view.findViewById(R.id.adressTV);
        ukInfo = (TextView) view.findViewById(R.id.ukInfo);
        personalKey = (TextView) view.findViewById(R.id.personKey);
        changeKey = (ImageButton) view.findViewById(R.id.changeKeyBut);
        addDataCounter = (Button) view.findViewById(R.id.addDataCounter);
        addDataCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Добавить обработку данных счетчика
            }
        });
        changeKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Обработать изменение ключа (передача нового ключа на сервер)
                String newKey = (String) personalKey.getText();
            }
        });
        fio.setText(Singleton.getInstance().getFio());
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String keyst = "";
        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            personalKey.setText(mSettings.getString(APP_PREFERENCES_COUNTER, "key"));
        }
        Address address = Singleton.getInstance().getAdress();
        adress.setText("Область: "+address.getArea()+" Город: "+address.getCity()+
                " Улица: "+address.getStreet()+" Дом: "+ address.getHouseNumber()+
                " Индекс: "+address.getIndex());
        return view;
    }
}

