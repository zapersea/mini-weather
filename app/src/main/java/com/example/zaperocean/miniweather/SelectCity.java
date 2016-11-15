package com.example.zaperocean.miniweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.zaperocean.miniweather.app.MyApplication;
import com.example.zaperocean.miniweather.bean.City;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zaper Ocean on 2016/10/18.
 */
public class SelectCity extends Activity implements View.OnClickListener{

    private ListView myListView;
    private ImageView mBackBtn;
    private List<City> mCityList;
    private String chooseCityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        MyApplication app=MyApplication.getInstance();
        mCityList=app.getCityList();
        myListView=(ListView)findViewById(R.id.list_view);
        myListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getData(mCityList)));
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                setTitle("点击第" + arg2 + "个项目");
                chooseCityCode=mCityList.get(arg2).getNumber();
                Intent i = new Intent();
                i.putExtra("cityCode", chooseCityCode);
                setResult(RESULT_OK, i);
                finish();
            }
        });
        mBackBtn = (ImageView) findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                Intent i = new Intent();
                i.putExtra("cityCode", "101160101");
                setResult(RESULT_OK, i);
                finish();
                break;
            default:
                break;
        }
    }

    private List<String> getData(List<City> mCityList){
        ArrayList<String> re=new ArrayList<>();
        for(City city:mCityList){
            re.add(city.getCity());
        }
        return re;
    }
}