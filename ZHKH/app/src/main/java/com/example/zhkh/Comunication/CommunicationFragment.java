package com.example.zhkh.Comunication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhkh.R;


public class CommunicationFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_communication;
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    private Button createTask;

    public CommunicationFragment() {
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        createTask = (Button) view.findViewById(R.id.createTask);
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_conteiner, new CreateTaskFragment()).addToBackStack(null).commit();
            }
        });
        initTabs();
        return view;
    }

    private void initTabs() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPageComunication);
        FragmentManager fragmentManager = getChildFragmentManager();
        TabComunicAdapter tabComunicAdapter = new TabComunicAdapter(fragmentManager);
        viewPager.setAdapter(tabComunicAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
