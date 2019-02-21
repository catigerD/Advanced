package com.example.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.network.cosnt.Constants;
import com.example.network.util.NetworkUtils;

/**
 * Created by dengchong on 19-2-21.
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {

//    private NetworkListener mNetworkListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Constants.MONITOR_INTENT_FILETER_ACTION.equalsIgnoreCase(action)) {
            NetworkType networkType = NetworkUtils.getNetworkType();
            NetworkManager.getInstance().notifyAllObserver(networkType);
        }
    }

//    public void setNetworkListener(NetworkListener networkListener) {
//        mNetworkListener = networkListener;
//    }
//
//    public interface NetworkListener{
//        void onConnection(NetworkType networkType);
//
//        void onDisconnection();
//    }

}
