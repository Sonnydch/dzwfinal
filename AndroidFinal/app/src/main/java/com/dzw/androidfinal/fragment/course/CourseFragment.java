package com.dzw.androidfinal.fragment.course;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dzw.androidfinal.R;
import com.dzw.androidfinal.entity.TimeTableModel;
import com.dzw.androidfinal.fragment.BaseBackFragment;
import com.dzw.androidfinal.view.TimeTableView;

import java.util.ArrayList;

public class CourseFragment extends BaseBackFragment {

    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;
    private TimeTableView mTimeTableView;

    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mList = new ArrayList<TimeTableModel>();
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mTimeTableView = (TimeTableView)view.findViewById(R.id.main_timetable_ly);

        initToolbarNav(mToolbar);
        mToolbar.setTitle("我的课表");


        mTimeTableView.setTimeTable(mList);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
