package com.example.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReciever br=new BroadcastReciever();
    Button bt;
    IntentFilter iF=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    LocalBroadcastManager lbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt=(Button)findViewById(R.id.button);
        lbm=LocalBroadcastManager.getInstance(getApplicationContext());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.addCategory("android.intent.category.DEFAULT");
                i.setAction("android.intent.action.customBroadcastName");
                lbm.sendBroadcast(i);
                Log.d("jinish", String.valueOf(i));
                Toast.makeText(MainActivity.this, "Broadcast!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(br, filter);
        registerReceiver(br, iF);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(br);
    }
}
