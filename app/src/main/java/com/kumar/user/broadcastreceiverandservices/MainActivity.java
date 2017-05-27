package com.kumar.user.broadcastreceiverandservices;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckPermissions();
        if (!myServices.isRunning){
            myServices.isRunning=true;
             intent=new Intent(this,myServices.class);
            startService(intent);
        }
    }

    public void buSendBroadCast(View view) {
        Intent intent=new Intent();
        intent.setAction("com.example.BroadCast");
        intent.putExtra("msg","hello from activity");
        sendBroadcast(intent);
        if (myServices.isRunning){
            myServices.isRunning=false;
          context. stopService(intent);
        }
    }
    void CheckPermissions(){
        if (Build.VERSION.SDK_INT>=23){
            if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!=
                    PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!=
                    PackageManager.PERMISSION_GRANTED) ){
                requestPermissions(new String[]{
                        Manifest.permission.RECEIVE_SMS},REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS=123;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
