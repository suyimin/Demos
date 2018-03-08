package com.xdroid.blogcodes.jni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xdroid.blogcodes.R;
import com.xdroid.blogcodes.jni.complicated.MaybeUtils;

public class Jni01Activity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni01);

        mTv = (TextView) findViewById(R.id.id_textview);
        JniFile jniFile = new JniFile();
        String res = jniFile.welcome("张鸿洋");
        mTv.setText(res);

        MaybeUtils generate = MaybeUtils.generate();
        Log.e("TAG", generate.getNum() + "");
    }


}
