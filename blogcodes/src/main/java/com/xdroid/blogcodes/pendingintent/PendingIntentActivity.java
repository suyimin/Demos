package com.xdroid.blogcodes.pendingintent;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xdroid.blogcodes.R;

public class PendingIntentActivity extends Activity {

    public String ACTION_ALARM = "action_alarm";

    private TextView tvShow;
    private Button btnSend;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_intent);
        tvShow = (TextView) findViewById(R.id.tvShow);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm();
            }
        });
    }

    public void alarm() {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmService.class);
        intent.setAction(ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT < 19) {
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent);
        } else {
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent);
        }
    }

}
