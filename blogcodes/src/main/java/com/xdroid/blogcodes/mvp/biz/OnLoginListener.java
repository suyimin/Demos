package com.xdroid.blogcodes.mvp.biz;

import com.xdroid.blogcodes.mvp.bean.User;

/**
 * Created by zhy on 15/6/19.
 */
public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}
