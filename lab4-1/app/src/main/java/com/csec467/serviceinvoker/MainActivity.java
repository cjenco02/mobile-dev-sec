package com.csec467.serviceinvoker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ServiceInvoker";
    private static final String SSID_BROADCAST = "com.csec467.SSID_BROADCAST";

    private final BroadcastReceiver ssidReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(SSID_BROADCAST)) {
                String ssid = intent.getStringExtra("SSID");
                Log.d(TAG, "Received SSID: " + ssid);
                Toast.makeText(context, "Connected SSID: " + ssid, Toast.LENGTH_LONG).show();
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button invokeButton = findViewById(R.id.invokeButton);
        invokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.csec467.servicecreator", "com.csec467.servicecreator.MyService");
                startService(intent);
                Log.d(TAG, "Service invoked");
            }
        });

        // Register Broadcast Receiver
        registerReceiver(ssidReceiver, new IntentFilter(SSID_BROADCAST), Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(ssidReceiver);
    }
}