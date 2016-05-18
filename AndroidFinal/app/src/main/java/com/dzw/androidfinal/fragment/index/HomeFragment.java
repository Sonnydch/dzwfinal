package com.dzw.androidfinal.fragment.index;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dzw.androidfinal.R;
import com.dzw.androidfinal.fragment.BaseMainFragment;
import com.dzw.androidfinal.fragment.chat.ChatFragment;
import com.dzw.androidfinal.fragment.course.CourseFragment;
import com.dzw.androidfinal.fragment.profile.ProfileFragment;
import com.dzw.androidfinal.game.Game;
import com.yongchun.library.view.ImageSelectorActivity;


public class HomeFragment extends BaseMainFragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;

    private AppCompatButton profileBtn;
    private AppCompatButton courseBtn;
    private AppCompatButton scoreBtn;
    private AppCompatButton albumBtn;
    private AppCompatButton chatBtn;
    private AppCompatButton jetBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();/*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {/*
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        profileBtn = (AppCompatButton)view.findViewById(R.id.profile_btn);
        courseBtn = (AppCompatButton)view.findViewById(R.id.course_btn);
        scoreBtn = (AppCompatButton)view.findViewById(R.id.score_btn);
        albumBtn = (AppCompatButton)view.findViewById(R.id.album_btn);
        chatBtn = (AppCompatButton)view.findViewById(R.id.chat_btn);
        jetBtn = (AppCompatButton)view.findViewById(R.id.jet_btn);

        profileBtn.setOnClickListener(this);
        courseBtn.setOnClickListener(this);
        scoreBtn.setOnClickListener(this);
        albumBtn.setOnClickListener(this);
        chatBtn.setOnClickListener(this);
        jetBtn.setOnClickListener(this);

        initToolbarNav(mToolbar, true);

        mToolbar.setTitle("主页");

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);/*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_btn:
                //TODO
                //toast("press profile button");
                start(ProfileFragment.newInstance());
                break;
            case R.id.album_btn:
                //TODO
                Intent intent = new Intent(_mActivity, ImageSelectorActivity.class);
                intent.putExtra(ImageSelectorActivity.EXTRA_MAX_SELECT_NUM, 1);
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_MULTIPLE);
                intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_PREVIEW, true);
                intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_CROP, false);
                startActivityForResult(intent, ImageSelectorActivity.REQUEST_IMAGE);
               // toast("press album_btn button");
                break;
            case R.id.score_btn:
                //TODO
                toast("press score_btn button");
                break;
            case R.id.course_btn:
                //toast("press course_btn button");

                start(CourseFragment.newInstance());
                //TODO
                break;
            case R.id.chat_btn:
                //toast("press chat_btn button");
                start(ChatFragment.newInstance());
                //TODO
                break;
            case R.id.jet_btn:
                //toast("press jet_btn button");
                Intent iintent = new Intent();

                iintent.setClass(_mActivity, Game.class);
                startActivity(iintent);
                //TODO
                break;
        }

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

    private void toast(String content){
        Toast.makeText(_mActivity,content,Toast.LENGTH_SHORT).show();
    }
}
