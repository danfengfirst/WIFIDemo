package com.danfeng.lucky.wifidemo.proxy;

import android.util.Log;

/**
 * Created by danfeng.wang on 2017/2/6.
 */

public class AndroidProgrammer implements IProgrammer {
    @Override
    public void program() {
        Log.e("程序员","写代码");
    }
}
