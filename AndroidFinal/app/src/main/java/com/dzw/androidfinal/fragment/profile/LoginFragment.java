package com.dzw.androidfinal.fragment.profile;

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
import android.widget.EditText;
import android.widget.Toast;

import com.dzw.androidfinal.R;
import com.dzw.androidfinal.fragment.BaseBackFragment;
import com.dzw.androidfinal.fragment.index.HomeFragment;
import com.dzw.androidfinal.util.Global;
import com.rengwuxian.materialedittext.MaterialEditText;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseBackFragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private Toolbar mToolbar;

    private MaterialEditText nameEt;
    private MaterialEditText passEt;
    private AppCompatButton dologBtn;
    private AppCompatButton gosignBtn;

    private String name;
    private String pass;


    private SharedPreferences mShare;


    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);

        nameEt = (MaterialEditText)view.findViewById(R.id.et_name);
        passEt = (MaterialEditText)view.findViewById(R.id.et_pass);

        dologBtn = (AppCompatButton)view.findViewById(R.id.dolog_btn);
        gosignBtn = (AppCompatButton)view.findViewById(R.id.gosign_btn);

        initToolbarNav(mToolbar);
        mToolbar.setTitle("登录");

        dologBtn.setOnClickListener(this);
        gosignBtn.setOnClickListener(this);
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
            case R.id.dolog_btn:
                //TODO
                if(check()){

                    name = nameEt.getText().toString();
                    pass = passEt.getText().toString();

                    mShare = Global.getDzwShare(_mActivity);
                    SharedPreferences.Editor mEditor = mShare.edit();
                    mEditor.putString(Global.NAME,name);
                    mEditor.commit();
                    //只储存名字
                    SupportFragment fragment = findFragment(HomeFragment.class);
                    if(fragment != null){
                        popTo(HomeFragment.class, false);
                    }else {
                        startWithFinish(HomeFragment.newInstance());
                    }
                }
                break;
            case R.id.gosign_btn:
                start(SignFragment.newInstance());
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
