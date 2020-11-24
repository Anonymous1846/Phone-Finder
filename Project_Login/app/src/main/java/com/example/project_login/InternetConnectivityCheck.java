package com.example.project_login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectivityCheck {
    Context context;
    ConnectivityManager connectivityManager;
    public InternetConnectivityCheck(Context context){
        this.context=context;
        connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public Boolean checkConn(){
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
                if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI||networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                    return true;
                }
        }
        return false;
    }
}
