package com.example.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals (intent.getAction())){
             boolean noWifi=intent.getBooleanExtra(
                     ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
             );
             if(noWifi){
                 Toast.makeText(context, "INTERNET IS TURNED OFF", Toast.LENGTH_SHORT).show();
             }
             else {
                 Toast.makeText(context, "INTERNET IS TURNED ON", Toast.LENGTH_SHORT).show();
             }
        }


        TextView status=((MainActivity)context).findViewById(R.id.status);
        TextView number=((MainActivity)context).findViewById(R.id.number);


        String action= intent.getAction();
        if(action !=null && action.equals(Intent.ACTION_BATTERY_CHANGED)){
            int st=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            String msg="";

            switch (st){
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    msg="UNKNOWN";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    msg="NOT CHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    msg="DISCHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    msg="CHARGING";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    msg="FULL";
                    break;
            }
            status.setText(msg);

            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            number.setText(level+" %");


        }


    }
}
