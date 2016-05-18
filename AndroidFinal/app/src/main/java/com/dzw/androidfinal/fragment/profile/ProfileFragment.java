package com.dzw.androidfinal.fragment.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dzw.androidfinal.R;
import com.dzw.androidfinal.fragment.BaseBackFragment;
import com.dzw.androidfinal.fragment.index.HomeFragment;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;

public class ProfileFragment extends BaseBackFragment {

    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;
    private ImageView image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        mToolbar = (Toolbar)view.findViewById(R.id.toolbar);
        image = (ImageView)view.findViewById(R.id.img_profile);
        initToolbarNav(mToolbar);
        mToolbar.inflateMenu(R.menu.profile_menu);
        mToolbar.setTitle("个人资料");
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //TODO
                popTo(HomeFragment.class, false);
                return true;
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_mActivity, ImageSelectorActivity.class);
                intent.putExtra(ImageSelectorActivity.EXTRA_MAX_SELECT_NUM, 1);
                intent.putExtra(ImageSelectorActivity.EXTRA_SELECT_MODE, ImageSelectorActivity.MODE_SINGLE);
                intent.putExtra(ImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_PREVIEW, true);
                intent.putExtra(ImageSelectorActivity.EXTRA_ENABLE_CROP, true);
                startActivityForResult(intent,ImageSelectorActivity.REQUEST_IMAGE);
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(_mActivity,"get result",Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE){
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
            File file =new File(images.get(0));//TODO to send image to cloud
            Glide.with(_mActivity)
                    .load(file)
                    .into(image);
        }
    }
}
