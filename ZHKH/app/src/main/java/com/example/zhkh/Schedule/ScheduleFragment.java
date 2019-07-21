package com.example.zhkh.Schedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhkh.R;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.Event;
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

        String[] arr1 = {"2019-07-21", "2019-07-27", "2019-07-31"};
        customCalendar.addAnEvent("2019-07-21", 1, getEvent(
                "Уборка придворовой территории", "2019-07-21",
                "Дворник будет убираться с 9.30-11.30",
                "Придворовая территория", "Уборка"));
        customCalendar.addAnEvent("2019-07-27", 1, getEvent(
                "Уборка придворовой территории", "2019-07-21",
                "Дворник будет убираться с 9.30-11.30",
                "Придворовая территория", "Уборка"));
        customCalendar.addAnEvent("2019-07-23", 1, getEvent(
                "Замена труб. Отключение горячей воды", "2019-08-10",
                "Замена труб на участке с д.2 до д.15 по Вашей улице",
                "Трубопровод", "Замена труб"  ));
        customCalendar.addAnEvent("2019-08-10", 1, getEvent(
                "Замена труб. Отключение горячей воды", "",
                "Окончание работ",
                "Трубопровод", "Замена труб"  ));
        return view;
    }

    private ArrayList<EventData> getEvent(String section, String submissionDate,
                                                 String remarks, String subject, String title) {
        ArrayList<EventData> events = new ArrayList<>();
        EventData eventData = new EventData();
        eventData.setSection("Замена труб. Отключение горячей воды");
        ArrayList<dataAboutDate> dataAboutDates = new ArrayList<>();
        dataAboutDate dataDate = new dataAboutDate();
        dataDate.setSubmissionDate("2019-08-10");
        dataDate.setRemarks("Замена труб на участке с д.2 до д.15 по Вашей улице");
        dataDate.setSubject("Трубопровод");
        dataDate.setTitle("Замена труб");
        dataAboutDates.add(dataDate);
        eventData.setData(dataAboutDates);
        events.add(eventData);
        return events;
    }

    private ArrayList<EventData> getEventDataListArr2(){
        ArrayList<EventData> events = new ArrayList<>();
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
