package com.dzw.androidfinal.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.dzw.androidfinal.R;
import com.dzw.androidfinal.entity.TimeTableModel;
import com.dzw.androidfinal.fragment.course.CourseFragment;
import com.dzw.androidfinal.util.Global;
import com.marshalchen.common.commonUtils.dbUtils.DataBaseUtils;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment extends SupportFragment {
    protected SQLiteDatabase myDb;

    protected List<TimeTableModel> mList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void detectChange() {
        Thread det = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Cursor myCursor;
                myCursor = myDb.rawQuery("SELECT * FROM timetablemodels", null);
                if(Global.all !=myCursor.getCount()){
                    startWithFinish(CourseFragment.newInstance());
                }

            }
        });
        det.start();
    }

    protected void initToolbarMenu(Toolbar toolbar) {
    /*    toolbar.inflateMenu(R.menu.hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        break;
                }
                return true;
            }
        });*/
    }
    protected void initData(){
        DataBaseUtils myDbUtil = new DataBaseUtils();

        initDb();
        long newRowId;
        Cursor myCursor;
        myCursor = myDb.rawQuery("SELECT * FROM timetablemodels", null);
        Global.all =  myCursor.getCount();  //check courses;

        if (Global.all == 0){
            insertCourse(0,10,11,1,"3-14","6-28","手机软件开发","李老师","健B111","1");
            insertCourse(1,3,4,4,"3-14","6-28","操作系统","李老师","博易C404","1");
            insertCourse(2,3,4,5,"3-14","6-28","JAVA","曹老师","博易C205","1");
            insertCourse(3,6,7,4,"3-14","6-28","手机软件开发","李老师","健B111","1");
            insertCourse(4,10,11,4,"3-14","6-28","现代国际关系与中国外交","嘛老师","法学B208","1");
            insertCourse(5,10,11,5,"3-14","6-28","ANDROID","李老师","郁文B306","1");
            Global.all = myCursor.getCount();
        }

        myCursor.moveToFirst();
        for(int i=0;i<myCursor.getCount();i++){
            mList.add(makeACourse(myCursor));
            myCursor.moveToNext();
        }

    }


    protected void insertCourse(int id,int startnum,int endnum,int week,String starttime,
                                String endtime,String name,String teacher,String classroom,
                                String weeknum) {
        long newRowId;
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("startnum",startnum);
        values.put("endnum",endnum);
        values.put("week",week);
        values.put("starttime",starttime);
        values.put("endtime",endtime);
        values.put("name", name);
        values.put("teacher",teacher);
        values.put("classroom",classroom);
        values.put("weeknum",weeknum);
        newRowId = myDb.insert("timetablemodels", null, values);
    }
    protected void deleteCourseById(int id){
        String[] args = {String.valueOf(id)};
        myDb.delete("timetablemodels","id=?",args);
    }

    protected TimeTableModel makeACourse(Cursor cursor){
        int  id = cursor.getInt(cursor.getColumnIndex("id"));
        int startum = cursor.getInt(cursor.getColumnIndex("startnum"));
        int endnum = cursor.getInt(cursor.getColumnIndex("endnum"));
        int week = cursor.getInt(cursor.getColumnIndex("week"));
        String starttime = cursor.getString(cursor.getColumnIndex("starttime"));
        String endtime = cursor.getString(cursor.getColumnIndex("endtime"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
        String classroom = cursor.getString(cursor.getColumnIndex("classroom"));
        String weeknum = cursor.getString(cursor.getColumnIndex("weeknum"));
        return new TimeTableModel(id,startum,endnum,week,starttime,endtime,name,teacher,classroom,weeknum);
    }

    protected void initDb() {
        myDb = 	getActivity().openOrCreateDatabase("dzwcourses", getActivity().MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS timetablemodels(" +
                "id INT,startnum INTEGER,endnum INTEGER,week INTEGER," +
                "starttime VARCHAR,endtime VARCHAR,name VARCHAR," +
                "teacher VARCHAR,classroom VARCHAR,weeknum VARCHAR" +
                ");");
        String path = myDb.getPath();
        String absolute = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.i("path", path);
    }
}
