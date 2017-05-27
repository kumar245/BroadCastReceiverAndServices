package com.kumar.user.broadcastreceiverandservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by User on 5/26/2017.
 */

public class myReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        if (intent.getAction().equalsIgnoreCase("com.example.BroadCast")){
            String msg=bundle.getString("msg");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            NewMessageNotification newMessageNotification= new NewMessageNotification();
            newMessageNotification.notify(context,msg,12);
        }
        if (intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")){
            if (bundle!=null){
                final Object[] pdusObj= (Object[]) bundle.get("pdus");
                SmsMessage[] messages=new SmsMessage[pdusObj.length];
                for (int i=0;i<messages.length;i++){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        String format=bundle.getString("format");
                        messages[i] =SmsMessage.createFromPdu((byte[]) pdusObj[i],format);
                    }
                    else {
                        messages[i]=SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    String senderNum=messages[i].getOriginatingAddress();
                    String message=messages[i].getMessageBody();
                    Toast.makeText(context,senderNum + ":" + message, Toast.LENGTH_SHORT).show();
                }

            }
            Toast.makeText(context, "SMS Received", Toast.LENGTH_SHORT).show();
        }


    }
}
