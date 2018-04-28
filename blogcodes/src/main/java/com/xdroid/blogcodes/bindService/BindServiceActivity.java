package com.xdroid.blogcodes.bindService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xdroid.blogcodes.R;

public class BindServiceActivity extends AppCompatActivity {
    private static final String TAG = "myMainActivityTag";
    MyService myService = null;
    private boolean isBound = false;
    private Bitmap mBitmap;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //禁止截屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_bind_service);
        mTextView = (TextView) findViewById(R.id.textView);

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
                //测试截屏工具类
                mBitmap = ScreenUtils.snapShotWithoutStatusBar(BindServiceActivity.this);
                if (mBitmap != null) {
                    mTextView.setBackground(new BitmapDrawable(getResources(), bg2WhiteBitmap(mBitmap)));
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

    /**
     * Bitmap 剪切成正方形，然后添加白边
     *
     * @param bitmap
     * @return
     */
    public Bitmap bg2WhiteBitmap(Bitmap bitmap) {
        int size = bitmap.getWidth() < bitmap.getHeight() ? bitmap.getWidth() : bitmap.getHeight();
        int num = 14;
        int size2 = size + num;
        // 背图
        Bitmap newBitmap = Bitmap.createBitmap(size2, size2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        // 生成白色的
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(bitmap, num / 2, num / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        // 画正方形的
        canvas.drawRect(0, 0, size2, size2, paint);
        return newBitmap;
    }
}
