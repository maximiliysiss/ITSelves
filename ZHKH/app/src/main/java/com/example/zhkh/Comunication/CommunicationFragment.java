package com.example.zhkh.Comunication;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhkh.R;


public class CommunicationFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_communication;
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
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
