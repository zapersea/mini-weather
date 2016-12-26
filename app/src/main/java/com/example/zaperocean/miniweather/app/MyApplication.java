package com.example.zaperocean.miniweather.app;


import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.example.zaperocean.miniweather.bean.City;
import com.example.zaperocean.miniweather.db.CityDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zaper Ocean on 2016/11/1.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApp";
    private static MyApplication myApplication;
    private CityDB mCityDB;
    private List<City> mCityList;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyApplication -> onCreate");

        myApplication = this;
        mCityDB = openCityDB();
        initCityList();
    }

//    // 单例模式
//    public static MyApplication getInstance() {
//        return myApplication;
//    }

    private CityDB openCityDB() {
        String path = "/data"
                + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases1"
                + File.separator
                + CityDB.CITY_DB_NAME;
        File db = new File(path);
        Log.d(TAG, path);

        // 如果不存在数据库路径和文件则从 Assets 中写入到指定位置
        if (!db.exists()) {
            String pathfolder = "/data"
                    + Environment.getDataDirectory().getAbsolutePath()
                    + File.separator + getPackageName()
                    + File.separator + "databases1"
                    + File.separator;
            File dirFirstFolder = new File(pathfolder);
            if (!dirFirstFolder.exists()) {
                dirFirstFolder.mkdirs();
                Log.i(TAG, "Make database directory");
            }
            try {
                InputStream is = getAssets().open("city.db");
                FileOutputStream fos = new FileOutputStream(db);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                fos.close();
                is.close();
                Log.i(TAG, "Create db file here");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return new CityDB(this, path);
    }

    // 如果城市很多那么初始化也需要时间，需要使用多线程
    private void initCityList() {
        mCityList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCityList = mCityDB.getAllCity();
//                int i = 0;
//                for (City city : mCityList) {
//                    i++;
//                    String cityName = city.getCity();
//                    String cityCode = city.getNumber();
//                    Log.d(TAG, cityCode + " : " + cityName);
//                }
//                Log.d(TAG, "i = " + i);
            }
        }).start();
    }

    public List<City> getmCityList() {
        return mCityList;
    }
}
