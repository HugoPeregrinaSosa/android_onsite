package com.mvillasenor.twitter.data.cloud;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class CloudUtils {


    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
