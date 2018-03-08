
#include <stdio.h>
#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_xdroid_blogcodes_jni_JniFile_welcome
        (JNIEnv *env, jobject object, jstring string) {
    //将java的字符串转化为c的字符串
    const char *str = (*env)->GetStringUTFChars(env, string, 0);
    char buffer[512];
    //将str赋值到buffer
    strlcpy(buffer, "jni => welcome:", sizeof buffer);
    //字符串拼接
    strlcat(buffer, str, sizeof buffer);
    return (*env)->NewStringUTF(env, buffer);
}


//参数2为jclass，jobject是用于指向java对象的引用，而static方法，只需要
//类名即可，保存的是类的引用
JNIEXPORT jobject JNICALL
Java_com_xdroid_blogcodes_jni_complicated_MaybeUtils_generate(JNIEnv *env, jclass type) {

    jclass targetClass;
    jmethodID mid;
    jobject newObject;
    //查找类
    targetClass = (*env)->FindClass(env, "com/xdroid/blogcodes/jni/complicated/MaybeUtils");
    //查找构造方法
    mid = (*env)->GetMethodID(env, targetClass, "<init>", "(I)V");
    //创建对象
    newObject = (*env)->NewObject(env, targetClass, mid, 100);
    //查找对象的方法
    mid = (*env)->GetMethodID(env, targetClass, "setNum", "(I)Lcom/xdroid/blogcodes/jni/complicated/MaybeUtils;");
    //调用方法
    newObject = (*env)->CallObjectMethod(env, newObject, mid, 188);
    return newObject;

}