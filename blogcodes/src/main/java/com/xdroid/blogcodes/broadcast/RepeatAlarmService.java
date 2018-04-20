package com.xdroid.blogcodes.broadcast;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

public class RepeatAlarmService extends Service {

    private ServiceHandler mServiceHandler = new ServiceHandler(Looper.myLooper());
    private int status;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int y = msg.arg1;
            PendingIntent receiver = bundle.getParcelable("receiver");
            try {
                Intent intent = new Intent();
                Bundle b = new Bundle();
                intent.putExtras(b);
                intent.putExtra("y",y);
                receiver.send(getApplicationContext(), status, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        int x = intent.getIntExtra("x", 0);
        int y = x + 10;
        Message msg = mServiceHandler.obtainMessage();
        msg.setData(bundle);
        msg.arg1 = y;
        mServiceHandler.sendMessage(msg);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}