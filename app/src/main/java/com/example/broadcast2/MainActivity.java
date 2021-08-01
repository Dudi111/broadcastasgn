package com.example.broadcast2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvnaam;
    private TextView tvumar;
    private Button mbtnreceive;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        tvnaam=findViewById(R.id.tvname);
        tvumar=findViewById(R.id.tvage);
        mbtnreceive=findViewById(R.id.btnreceive);
        registerLocalreceiver();
        mbtnreceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("Name and Age");
                intent.putExtra("Key1","Praveen Dudi");
                intent.putExtra("Key2","AGE:27");
                localBroadcastManager.sendBroadcast(intent);

            }
        });
    }
    private void registerLocalreceiver(){
        localReceiver=new LocalReceiver();
        IntentFilter intentFilter=new IntentFilter("Name and Age");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    private class LocalReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent !=null){
                String data=intent.getStringExtra("Key1");
                String data2=intent.getStringExtra("Key2");
                tvnaam.setText(data);
                tvumar.setText(data2);
            }

        }
    }
}