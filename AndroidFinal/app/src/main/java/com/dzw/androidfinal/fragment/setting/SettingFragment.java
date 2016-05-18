package com.dzw.androidfinal.fragment.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dzw.androidfinal.R;
import com.dzw.androidfinal.fragment.BaseBackFragment;
import com.dzw.androidfinal.fragment.index.HomeFragment;
import com.dzw.androidfinal.fragment.profile.LoginFragment;
import com.dzw.androidfinal.util.Global;

import me.yokeyword.fragmentation.SupportFragment;

public class SettingFragment extends BaseBackFragment implements View.OnClickListener{


    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;

    private AppCompatButton gologinBtn;
    private AppCompatButton logoutBtn;
    private TextView labelTv;
    private View divider;

    private SharedPreferences mShare;

    private String name;

    private TextView nameTv;

    public SettingFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();/*
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
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        gologinBtn = (AppCompatButton)view.findViewById(R.id.gologin_btn);
        nameTv = (TextView)view.findViewById(R.id.name_tv);
        logoutBtn = (AppCompatButton)view.findViewById(R.id.logout_btn);

        labelTv = (TextView)view.findViewById(R.id.textView5);
        divider = view.findViewById(R.id.divider);

        mShare = Global.getDzwShare(_mActivity);

        name = mShare.getString(Global.NAME,"");
        if ("".equalsIgnoreCase(name)){//没有登录
            gologinBtn.setVisibility(View.VISIBLE);
            labelTv.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
            nameTv.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.GONE);
            gologinBtn.setOnClickListener(this);
        }else {//已登录
            gologinBtn.setVisibility(View.GONE);
            nameTv.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
            nameTv.setText(name);
            labelTv.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            logoutBtn.setOnClickListener(this);
        }
        initToolbarNav(mToolbar);
        mToolbar.setTitle("设置");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gologin_btn:
                //TODO
                start(LoginFragment.newInstance());
                break;
            case R.id.logout_btn:
                doLogout();
                break;
        }
    }

    private void doLogout(){
        SharedPreferences.Editor mEditor = mShare.edit();
        mEditor.putString(Global.NAME,"");
        mEditor.commit();
        popTo(HomeFragment.class,false);
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
