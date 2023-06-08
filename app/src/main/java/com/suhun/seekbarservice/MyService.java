package com.suhun.seekbarservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private String tag = MyService.class.getSimpleName();
    public int counter;
    public int maxCounter;
    private UIHandler uiHandler;
    private Timer timer;

    private class MyTask extends TimerTask{
        @Override
        public void run() {
            if(counter >= maxCounter){
                cancel();
            }else{
                uiHandler.sendEmptyMessage(0);
            }
        }
    }

    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            counter++;
            Intent intent = new Intent("Suhun");
            intent.putExtra("currentCounter", counter);
            sendBroadcast(intent);
        }
    }
    public MyService() {
        uiHandler = new UIHandler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        counter = 0;
        maxCounter = 100;
        timer = new Timer();
        timer.schedule(new MyTask(), 100, 100);
        Intent intent = new Intent("Suhun");
        intent.putExtra("counterMax", maxCounter);
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            counter = 0;
            timer.cancel();
            timer.purge();
            timer = null;

        }
    }
}