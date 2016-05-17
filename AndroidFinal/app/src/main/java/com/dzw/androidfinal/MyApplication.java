package com.dzw.androidfinal;

import org.litepal.LitePalApplication;

/**
 * Created by sonny on 16-5-17.
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        //initdata here;
    }
}
