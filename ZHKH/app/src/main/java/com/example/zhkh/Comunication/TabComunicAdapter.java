package com.example.zhkh.Comunication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
