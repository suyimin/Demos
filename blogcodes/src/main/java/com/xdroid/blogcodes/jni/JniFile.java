package com.xdroid.blogcodes.jni;

public class JniFile {

	static {
		System.loadLibrary("hello");
	}

	public native String welcome(String str);
}
