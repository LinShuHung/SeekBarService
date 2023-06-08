package com.suhun.seekbarservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import kotlinx.coroutines.channels.BroadcastKt;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView showCounterText;
    private SeekBar seekBar;
    private MyRecevier myRecevier;

    private class MyRecevier extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int maxCounter = intent.getIntExtra("counterMax", -1);
            int currentCounter = intent.getIntExtra("currentCounter", -1);
            if(maxCounter>0){
                seekBar.setMax(maxCounter);
            }
            if(currentCounter>0){
                showCounterText.setText(""+currentCounter);
                seekBar.setProgress(currentCounter);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        showCounterText = findViewById(R.id.showCounter);
        seekBar = findViewById(R.id.seek);
        myRecevier = new MyRecevier();
        registerReceiver(myRecevier, new IntentFilter("Suhun"));
    }

    public void startServiceFun(View view){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    public void stopServiceFun(View view){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        seekBar.setProgress(0);
        showCounterText.setText(""+0);
    }
}