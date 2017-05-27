package com.kumar.user.broadcastreceiverandservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by User on 5/26/2017.
 */

public class myServices extends IntentService {
    public static boolean isRunning=false;
    public myServices() {
        super("myservices");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (isRunning){
            Intent BroadIntent=new Intent();
            BroadIntent.setAction("com.example.BroadCast");
            BroadIntent.putExtra("msg","hello from service");
            sendBroadcast(BroadIntent);

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
