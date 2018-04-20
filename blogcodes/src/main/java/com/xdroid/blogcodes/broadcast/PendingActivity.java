package com.xdroid.blogcodes.broadcast;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xdroid.blogcodes.R;

public class PendingActivity extends Activity {

    private AlarmReceiver mRev;

    private TextView tvShow;
    private Button btnSend;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);

        tvShow = (TextView) findViewById(R.id.tvShow);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                alarm();
            }
        });
        registerReceiver();
    }

    public void alarm() {
        Intent intent = new Intent();
        intent.setAction("xxx.xxx.xxx");
        intent.putExtra("scheduleText", String.valueOf(position));
        System.out.println("----设置闹钟----" + position);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bundle bundle = new Bundle();
        bundle.putParcelable("receiver", pendingIntent);
        Intent intentService = new Intent(getApplicationContext(), RepeatAlarmService.class);
        intentService.putExtras(bundle);
        intentService.putExtra("x", position);
        System.out.println("------参数：" + position);
        startService(intentService);
    }

    public class AlarmReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("xxx.xxx.xxx".equals(intent.getAction())) {
                System.out.println("------闹钟：" + intent.getStringExtra("scheduleText"));
                int y = intent.getIntExtra("y", 0);
                System.out.println("------结果：" + y);
            }
        }
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("xxx.xxx.xxx");
        mRev = new AlarmReceiver();
        registerReceiver(mRev, filter);
    }

    private void unregisterReceiver() {
        if (mRev != null) {
            unregisterReceiver(mRev);
            mRev = null;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver();
        super.onDestroy();
    }
}
