package com.xdroid.blogcodes.bindService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xdroid.blogcodes.R;

public class BindServiceActivity extends AppCompatActivity {
    private static final String TAG = "myMainActivityTag";
    MyService myService = null;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "onServiceConnected");
                myService = ((MyService.LocalBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "onServiceDisconnected");
            }
        };

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        Button buttonEnd = (Button) findViewById(R.id.buttonStop);
        Button buttonUsing = (Button) findViewById(R.id.buttonUse);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BindServiceActivity.this, MyService.class);
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                isBound = true;
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
            }
        });
        buttonUsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myService != null) {
                    Log.d(TAG, "Using Service:" + myService.add(1, 3));
                }

            }
        });

    }
}
