package com.suhun.seekbarservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView showCounterText;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        showCounterText = findViewById(R.id.showCounter);
        seekBar = findViewById(R.id.seek);
    }

    public void startServiceFun(View view){

    }

    public void stopServiceFun(View view){

    }
}