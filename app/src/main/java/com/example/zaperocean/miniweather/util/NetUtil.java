package com.example.zaperocean.miniweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * Created by Zaper Ocean on 2016/10/11.
 */
public class NetUtil {
    public static final int NETWORN_NONE=0;
    public static final int NETWORN_WIFI=1;
    public static final int NETWORN_MOBILE=2;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //WIFI
        State state=connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(state==State.CONNECTED || state==State.CONNECTING)
            return  NETWORN_WIFI;
        //MOBILE
        state=connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if(state==State.CONNECTED || state==State.CONNECTING)
            return  NETWORN_MOBILE;
        return  NETWORN_NONE;
    }
}