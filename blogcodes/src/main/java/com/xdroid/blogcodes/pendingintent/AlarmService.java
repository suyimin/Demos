package com.xdroid.blogcodes.pendingintent;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class AlarmService extends Service {

    private Handler mHanler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHanler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AlarmService.this, "闹钟来啦", Toast.LENGTH_SHORT).show();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}