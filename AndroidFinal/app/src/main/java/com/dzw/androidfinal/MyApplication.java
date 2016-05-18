package com.dzw.androidfinal;

import com.dzw.androidfinal.entity.Account;
import com.dzw.androidfinal.entity.Profile;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by sonny on 16-5-17.
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        //initdata here;
        initData();
    }


    private void initData(){
        List<Account> mAccounts;
        List<Profile> mProfiles;
        mAccounts = DataSupport.findAll(Account.class);
        mProfiles = DataSupport.findAll(Profile.class);
        if (mAccounts.size() == 0){
            String[] names={
                    "dzw001","dzw002","dzw003"
            };
            String pass = "123";
            Account account;
            for(int i=0;i<3;i++){
                account = new Account(i,names[i],pass);
                account.save();
            }
        }
        if (mProfiles.size() == 0){
            String[] names= {
                    "杜子文一号","杜子文二号","杜子文七号"
            };
            String hobby = "踢球";
            Date date = new Date();
            Profile profile;
            for (int i=0;i<names.length;i++){
                profile = new Profile(i,date,hobby,i,names[i]);
                profile.save();
            }
        }
    }
}
