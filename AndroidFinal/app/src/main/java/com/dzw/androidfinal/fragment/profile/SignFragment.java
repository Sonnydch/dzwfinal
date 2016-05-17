package com.dzw.androidfinal.fragment.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dzw.androidfinal.R;
import com.dzw.androidfinal.fragment.BaseBackFragment;
import com.dzw.androidfinal.fragment.index.HomeFragment;

import me.yokeyword.fragmentation.SupportFragment;

public class SignFragment extends BaseBackFragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;

    private EditText nameEt;
    private EditText passEt;
    private EditText confirmEt;

    private Button dosignBtn;
    private Button gologBtn;

    private String name;
    private String pass;
    private String confirm;

    public SignFragment() {
        // Required empty public constructor
    }

    public static SignFragment newInstance() {
        SignFragment fragment = new SignFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);

        nameEt = (EditText)view.findViewById(R.id.s_et_name);
        passEt = (EditText)view.findViewById(R.id.s_et_pass);
        confirmEt = (EditText)view.findViewById(R.id.s_et_confirm);

        dosignBtn = (Button)view.findViewById(R.id.do_sign_btn);
        gologBtn = (Button)view.findViewById(R.id.golog_btn);


        dosignBtn.setOnClickListener(this);
        gologBtn.setOnClickListener(this);
        initToolbarNav(mToolbar);

        mToolbar.setTitle("注册");
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
            case R.id.do_sign_btn:
                //TODO;
                if (check()){
                    SupportFragment fragment = findFragment(HomeFragment.class);
                    if(fragment != null){
                        popTo(HomeFragment.class, false);
                    }else {
                        startWithFinish(HomeFragment.newInstance());
                    }
                }
                break;
            case R.id.golog_btn:
                pop();
                break;
        }
    }


    private boolean check(){
        if ("".equalsIgnoreCase(nameEt.getText().toString())){
            toast("用户名不能为空");
            return false;
        }
        if ("".equalsIgnoreCase(passEt.getText().toString())){
            toast("密码不能为空");
            return false;
        }
        if (!passEt.getText().toString().equalsIgnoreCase(confirmEt.getText().toString())){
            toast("两次密码不一致!");
            return false;
        }
        return true;
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
        Toast.makeText(_mActivity, content, Toast.LENGTH_SHORT).show();
    }
}
