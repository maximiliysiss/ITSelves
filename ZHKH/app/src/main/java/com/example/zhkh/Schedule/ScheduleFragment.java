package com.example.zhkh.Schedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhkh.R;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private CustomCalendar customCalendar;
    public ScheduleFragment() {
        // Required empty public constructor
    }
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
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
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        customCalendar = (CustomCalendar) view.findViewById(R.id.customCalendar);

        String[] arr = {"2019-08-10", "2019-08-11", "2019-08-15", "2019-08-16", "2019-08-25"};
        for (int i = 0; i < 5; i++) {
            int eventCount = 3;
            customCalendar.addAnEvent(arr[i], eventCount, getEventDataList(eventCount));
        }
        return view;
    }

    private ArrayList<EventData> getEventDataList(int eventCount){
        ArrayList<EventData> events = new ArrayList<>();
        for(int i = 0; i < eventCount; i++){
            EventData eventData = new EventData();
            eventData.setSection("section");
            ArrayList<dataAboutDate> list = new ArrayList<>();
            dataAboutDate dataDate = new dataAboutDate();
            dataDate.setSubmissionDate("2019-08-18");
            dataDate.setRemarks("remark");
            dataDate.setSubject("subject");
            dataDate.setTitle("title");
            list.add(dataDate);
            eventData.setData(list);
            events.add(eventData);
        }
        return events;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
