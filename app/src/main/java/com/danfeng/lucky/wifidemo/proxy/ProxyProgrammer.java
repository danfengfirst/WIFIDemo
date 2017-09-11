package com.danfeng.lucky.wifidemo.proxy;

import android.util.Log;

/**
 * Created by danfeng.wang on 2017/2/6.
 */

public class ProxyProgrammer {
    AndroidProgrammer programmer;

    public ProxyProgrammer(AndroidProgrammer programmer) {
        this.programmer = programmer;
    }

    public void program(){
        programmer.program();
        Log.e("你猜这是什么","答：静态代理");
    }
}
