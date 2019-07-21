package com.example.zhkh.Comunication;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class TabComunicAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabComunicAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Завершенные заявки","Открытые заявки"};
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                try {
                    return OpenTaskFragment.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            case 1:
                try {
                    return CloseTaskFragment.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
