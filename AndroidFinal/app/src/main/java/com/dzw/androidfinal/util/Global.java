package com.dzw.androidfinal.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sonny on 16-5-18.
 */
public class Global {
    public static SharedPreferences dzwShare;
    public static int all;
    public static final String SHARE_NAME = "dzwshare";
    public static final String NAME = "name";
    public static final String STU_ID = "stuid";
    public static final String EMAIL = "email";

    public static SharedPreferences getDzwShare(Context context){
        if (dzwShare == null){
            dzwShare = context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
            return dzwShare;
        }
        return dzwShare;
    }

}
