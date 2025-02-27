package com.csec467.servicecreator;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    public static final String SSID_BROADCAST = "com.csec467.SSID_BROADCAST";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(this::sendSSIDBroadcast).start();
        return START_NOT_STICKY;
    }

    private void sendSSIDBroadcast() {
        try {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();

            Intent broadcastIntent = new Intent(SSID_BROADCAST);
            broadcastIntent.putExtra("SSID", ssid);
            sendBroadcast(broadcastIntent);
            Log.d(TAG, "Broadcast sent with SSID: " + ssid);
        } catch (Exception e) {
            Log.e(TAG, "Error getting SSID", e);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
}
