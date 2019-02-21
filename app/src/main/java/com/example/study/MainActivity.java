package com.example.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.network.NetworkBroadcastReceiver;
import com.example.network.NetworkManager;
import com.example.network.NetworkType;
import com.example.network.annotation.Network;
import com.example.network.util.NetworkUtils;

public class MainActivity extends AppCompatActivity/* implements NetworkBroadcastReceiver.NetworkListener*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NetworkManager.getInstance().setNetworkListener(this);
        NetworkManager.getInstance().registerObserver(this);
    }

    @Network(target = NetworkType.AUTO)
    public void onNetwork(NetworkType networkType) {
        switch (networkType) {
            case WIFI:
                Log.i(NetworkUtils.TAG, "onConnection: " + networkType.name());
                Toast.makeText(this, "onConnection", Toast.LENGTH_SHORT).show();
                break;
            case GPRS:
                break;
            case NO:
                Log.i(NetworkUtils.TAG, "onDisconnection: ");
                Toast.makeText(this, "onDisconnection", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getInstance().unRegisterObserver(this);
    }

    /*@Override
    public void onConnection(NetworkType networkType) {
        Log.i(NetworkUtils.TAG, "onConnection: " + networkType.name());
        Toast.makeText(this, "onConnection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnection() {
        Log.i(NetworkUtils.TAG, "onDisconnection: ");
        Toast.makeText(this, "onDisconnection", Toast.LENGTH_SHORT).show();
    }*/
}
