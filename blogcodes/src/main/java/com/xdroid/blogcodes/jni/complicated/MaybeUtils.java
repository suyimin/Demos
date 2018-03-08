package com.xdroid.blogcodes.jni.complicated;

import android.util.Log;

public class MaybeUtils {
    public static native MaybeUtils generate();

    private int num;

    public MaybeUtils(int num) {
        this.num = num;
    }


    public MaybeUtils setNum(int num) {
        this.num = num;
        Log.e("TAG", "call by native method ");
        return this;
    }

    public int getNum() {
        return num;
    }


}

