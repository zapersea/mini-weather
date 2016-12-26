package com.example.zaperocean.miniweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Zaper Ocean on 2016/10/11.
 */
public class NetUtil {
    public enum NetworkState {
        NETWORK_NONE,
        NETWORK_WIFI,
        NETWORK_MOBILE
    }

    public static NetworkState getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return NetworkState.NETWORK_NONE;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return NetworkState.NETWORK_MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetworkState.NETWORK_WIFI;
        }
        return NetworkState.NETWORK_NONE;
    }
}