package com.xdroid.blogcodes.bindService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    //logcat tag
    private static final String TAG="myServiceTag";

    //onBind返回的IBinder接口对象
    private LocalBinder myBinder=new LocalBinder();

    //计算器binder
    public class LocalBinder extends Binder{
        MyService getService() {
            return MyService.this;
        }
    }


    public MyService() {
    }

    public int add(int x,int y){
        return x+y;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind()");
        super.onRebind(intent);
    }
}